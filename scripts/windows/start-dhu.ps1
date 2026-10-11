# BAuto: connect Android Auto on a USB-connected Android phone to the Windows DHU.
# Prerequisite: phone Android Auto > Developer settings > Start head unit server.
param(
    [string]$Apk,
    [string]$SdkRoot = (Join-Path $env:LOCALAPPDATA "Android\Sdk")
)
$ErrorActionPreference = 'Stop'
Set-StrictMode -Version Latest

$adb = Join-Path $SdkRoot "platform-tools\adb.exe"
$dhu = Join-Path $SdkRoot "extras\google\auto\desktop-head-unit.exe"
if (-not (Test-Path $adb) -or -not (Test-Path $dhu)) {
    throw "ADB/DHU assenti. Prima esegui scripts\windows\install-dhu.ps1."
}

Write-Host "Dispositivi connessi:"
$devices = & $adb devices
$devices | ForEach-Object { Write-Host $_ }
$authorized = @($devices | Where-Object { $_ -match '^[^\s]+\s+device$' })
if ($authorized.Count -ne 1) {
    throw "Richiesto esattamente un dispositivo Android autorizzato. Sblocca il telefono, attiva Debug USB e conferma la chiave RSA (adb devices = device)."
}
if ($Apk) {
    $resolvedApk = (Resolve-Path -LiteralPath $Apk -ErrorAction Stop).Path
    Write-Host "Installo il debug APK..."
    & $adb install -r $resolvedApk
    if ($LASTEXITCODE -ne 0) { throw "adb install fallito." }
}
Write-Host "Sul telefono: Android Auto > menu sviluppatore > Start head unit server."
Write-Host "Assicurati che la notifica del server Android Auto sia presente e che lo schermo sia sbloccato."
& $adb forward tcp:5277 tcp:5277
if ($LASTEXITCODE -ne 0) { throw "adb forward fallito." }
Write-Host "Avvio DHU..."
& $dhu
if ($LASTEXITCODE -ne 0) { throw "DHU terminato con exit code $LASTEXITCODE. Verifica il server Android Auto sul telefono." }
