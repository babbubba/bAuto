# BAuto: install the minimal official Android SDK components required for DHU.
# Windows PowerShell 5.1+ / PowerShell 7; requires JDK 17+ in PATH.
# This does NOT install Android Studio, Android Emulator or a full Android build toolchain.
# Review and accept Android SDK terms before using: https://developer.android.com/studio
param(
    [string]$SdkRoot = (Join-Path $env:LOCALAPPDATA "Android\Sdk")
)
$ErrorActionPreference = 'Stop'
Set-StrictMode -Version Latest

if (-not (Get-Command java -ErrorAction SilentlyContinue)) {
    throw 'Java JDK 17+ non trovato nel PATH. Installa JDK 17 (es. winget install EclipseAdoptium.Temurin.17.JDK), riapri PowerShell e riprova.'
}
Write-Host "Java:"
& java -version

$manager = Join-Path $SdkRoot "cmdline-tools\latest\bin\sdkmanager.bat"
if (-not (Test-Path $manager)) {
    $toolUrl = "https://dl.google.com/android/repository/commandlinetools-win-15859902_latest.zip"
    # SHA-256 published on the official Android Studio download page on 2026-10-11.
    $expectedSha256 = "90ae805d20434428bffcb699c290860f19bb5f66a67e6b330067e3de801fb04a"
    $temp = Join-Path ([System.IO.Path]::GetTempPath()) ("bauto-android-sdk-" + [guid]::NewGuid().ToString("N"))
    $zip = Join-Path $temp "cmdline-tools.zip"
    New-Item -ItemType Directory -Force -Path $temp | Out-Null
    try {
        Write-Host "Scarico i Command-line Tools ufficiali da Google..."
        Invoke-WebRequest -Uri $toolUrl -OutFile $zip
        $actual = (Get-FileHash -Algorithm SHA256 -Path $zip).Hash.ToLowerInvariant()
        if ($actual -ne $expectedSha256) {
            throw "Verifica SHA-256 fallita: atteso $expectedSha256, ricevuto $actual. Nessun file installato."
        }
        Expand-Archive -LiteralPath $zip -DestinationPath (Join-Path $temp "unpacked") -Force
        $source = Join-Path $temp "unpacked\cmdline-tools"
        if (-not (Test-Path (Join-Path $source "bin\sdkmanager.bat"))) {
            throw "Archivio Google inatteso: sdkmanager.bat non trovato."
        }
        $targetTools = Join-Path $SdkRoot "cmdline-tools\latest"
        New-Item -ItemType Directory -Force -Path (Split-Path -Parent $targetTools) | Out-Null
        Move-Item -Path $source -Destination $targetTools
    }
    finally {
        Remove-Item $temp -Recurse -Force -ErrorAction SilentlyContinue
    }
}

$env:ANDROID_HOME = $SdkRoot
[Environment]::SetEnvironmentVariable("ANDROID_HOME", $SdkRoot, "User")
Write-Host "SDK: $SdkRoot"
Write-Host "Accetta le licenze SDK solo dopo averle lette."
& $manager "--sdk_root=$SdkRoot" --licenses
if ($LASTEXITCODE -ne 0) { throw "Accettazione licenze terminata con errore $LASTEXITCODE." }

Write-Host "Installazione ADB e Android Auto Desktop Head Unit (senza Android Studio)..."
& $manager "--sdk_root=$SdkRoot" "platform-tools" "extras;google;auto"
if ($LASTEXITCODE -ne 0) { throw "Installazione SDK fallita con exit code $LASTEXITCODE." }

$adb = Join-Path $SdkRoot "platform-tools\adb.exe"
$dhu = Join-Path $SdkRoot "extras\google\auto\desktop-head-unit.exe"
if (-not (Test-Path $adb)) { throw "ADB non presente in $adb." }
if (-not (Test-Path $dhu)) { throw "DHU non presente in $dhu; controlla se il pacchetto extras;google;auto è disponibile nel repository SDK." }

Write-Host "Installazione completata." -ForegroundColor Green
Write-Host "ADB: $adb"
Write-Host "DHU: $dhu"
Write-Host "Prossimo passo: abilita Debug USB e avvia Head Unit Server da Android Auto sul telefono."
Write-Host "Quindi: .\scripts\windows\start-dhu.ps1 -Apk <percorso\app-debug.apk>"
