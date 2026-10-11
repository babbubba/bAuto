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
