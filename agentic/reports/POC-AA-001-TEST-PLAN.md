# POC-AA-001 — Test and evidence plan

**Status:** NOT RUN (as of 2026-10-11). This file is a test specification, not proof.

## Environment to record

- git SHA, AGP/Kotlin/Gradle/Car App Library versions
- Android device / OS version / Android Auto version
- DHU version and host Car API level
- emulator/device manufacturer, screen resolution
- tester/date

## Tests

| ID | Action | Expected result |
|---|---|---|
| P001 | `gradle :app:assembleDebug` | Build succeeds |
| P002 | `gradle :app:testDebugUnitTest` | JUnit tests pass |
| P003 | `gradle :app:lintDebug` | No blocking lint issues |
| P004 | Install APK on connected Android phone | Installs, phone information screen visible |
| P005 | Start Desktop Head Unit, open BAuto | POI list and map visible within 10s |
| P006 | Inspect three rows | Correct sample titles, distance spans and markers |
| P007 | Tap row | Detail template opens; app doesn't crash |
| P008 | Tap Back | Returns to list, <=5 template depth |
| P009 | Request refresh | Reorders list, redraws map/list without new flow |
| P010 | Toggle dark/light if supported | Appropriate contrast/legibility |
| P011 | Stop/reconnect DHU | Restarts safely, no infinite loading |
| P012 | Install release build for host validation test | Non-trusted hosts rejected, legitimate Android Auto host accepted |
| P013 | Cold launch and button timings | DR-2 <=10s, DR-1 <=2s |
| P014 | Replace demo data with real POIs | Confirm meaningful journey-related locations before Play review |

## Commands

From the project folder after installing Java 17+, Gradle 8.13 and Android SDK 36:

```bash
gradle :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb logcat -s BAutoPoi AndroidRuntime CarApp
```

On Windows PowerShell use `gradle.bat` or the Gradle command configured in Android Studio.

Read DHU setup:
https://developer.android.com/training/cars/testing/dhu

## Evidence files

Under `agentic/reports/evidence/POC-AA-001/`, store environment.md, results.md, test logs and screenshot references. Do not commit personal data from Logcat.

## Critical pass condition

A successful build or DHU render does not establish Google Play eligibility. The POI semantics and category still require a compliant real-data scenario and Play review.


## Primo dispositivo di test: Android 13

**Confermato dall'utente:** smartphone Android 13 (API level 33).  
**Modello esatto:** da confermare, non inferire il nome dal riferimento informale alla serie GT.  
**Host di sviluppo previsto:** Windows con Android Studio.

Il progetto utilizza `minSdk 28` e `targetSdk 36`: Android 13 è compatibile con il requisito minimo. Non equivale a test riuscito né prova la disponibilità di Android Auto/host sul device.

### Procedura Windows — DHU via ADB

1. Installare Android Studio, `Android SDK Platform 36`, `Android SDK Platform-Tools` e l'elemento SDK Tools **Android Auto Desktop Head Unit emulator**.
2. Sul telefono Android 13 abilitare Opzioni sviluppatore e **Debug USB**. Collegare il telefono al PC con cavo dati, sbloccarlo e confermare la chiave RSA quando richiesto.
3. Installare il debug APK generato dalla CI (artefatto `bauto-poi-debug-apk` scaricabile dalla run riuscita) dopo aver estratto l'APK in locale.
4. Aprire le impostazioni di Android Auto sul telefono, abilitare modalità sviluppatore di Android Auto; dal menu sviluppatore usare **Start head unit server**.
5. In PowerShell, con `adb` nel PATH, verificare il collegamento e creare il tunnel:

   ```powershell
   adb devices
   adb install -r ".\app-debug.apk"
   adb forward tcp:5277 tcp:5277
   ```

6. Eseguire `desktop-head-unit.exe` da `%LOCALAPPDATA%\Android\Sdk\extras\google\auto\` (per installazione SDK standard; verificare il percorso effettivo). Eseguire dal prompt nella directory o specificare il percorso completo.
7. Aprire BAuto POI PoC nel launcher del DHU, se visibile; eseguire scenari P005-P011 e annotare errori/host/Car API.
8. Se l'app non compare, **non interpretarlo automaticamente come errore Kotlin**: verificare modalità sviluppatore, compatibilità host, trusted source e policy relativa alle app basate su Car App Library. L'opzione Unknown sources non abilita genericamente queste app su auto reali.

Google documenta anche `desktop-head-unit.exe --usb` (Accessory Mode) come opzione DHU 2.x; su Windows può richiedere WinUSB e interferire con ADB. **Preferire inizialmente il tunnel ADB**, più facile da verificare senza modificare driver USB.

Fonte: https://developer.android.com/training/cars/testing/dhu  
Trusted distribution: https://developer.android.com/training/cars/testing

### Report primo test

Nel file `agentic/reports/evidence/POC-AA-001/environment.md` registrare:
- modello dispositivo;
- Android 13, build/security patch;
- versione Android Auto, DHU, Google Play Services;
- comando `adb devices` (mascherando seriali nei log pubblici);
- outcome APK install, launcher visibility, render POI e screenshot anonimi.
