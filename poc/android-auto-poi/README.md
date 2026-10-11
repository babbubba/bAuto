# BAuto — Android Auto POI PoC

**Experimental, not approved for distribution.** No microphone, AI, persistent user tasks or location API in this phase.

## Purpose

Confirm that BAuto can project a host-rendered `PlaceListMapTemplate` for location-bound errands and support row details plus refresh on Android Auto. This is POC-AA-001, not a production app.

The demo dataset is synthetic, with approximate Torino coordinates; distance labels refer to a **demonstration origin** (not the car's current GPS). No POI business identity, opening time, current location or real user task is asserted. Do not use the data for navigation.

## Toolchain

- Android Studio + Android SDK Platform 36 and Build Tools.
- JDK 17 (AGP 8.13 also runs on compatible later JDKs).
- Gradle 8.13.
- Android Gradle Plugin 8.13.2, Kotlin 2.2.21.
- AndroidX Car App Library 1.7.0 stable.
- Android phone API 28+, Android Auto app and Desktop Head Unit.

**Gradle wrapper:** this bootstrap stores reproducible versions and GitHub Actions installs Gradle 8.13. A wrapper binary is not checked in yet. Generate locally once using `gradle wrapper --gradle-version 8.13` and commit the standard Gradle wrapper scripts/JAR in a follow-up; do not fabricate binary files.

## Build

Open **this folder** (`poc/android-auto-poi`) in Android Studio and use configured Gradle 8.13, or from a shell with Gradle installed:

```sh
gradle :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

Alternatively review the GitHub Actions job `Android Auto POI PoC`, which runs the same checks on an Android SDK-enabled runner.

## Test on Android Auto

1. On the Android phone, enable Android Auto developer settings (use Google's official DHU instructions).
2. Install the debug APK and run Android Auto Desktop Head Unit.
3. Launch `BAuto POI PoC` from the car launcher.
4. Verify three demo rows, host pins, list/details/back and refresh (toggles order).
5. Collect logs, screenshots and timings (see [test plan](../../agentic/reports/POC-AA-001-TEST-PLAN.md)).

DHU docs: https://developer.android.com/training/cars/testing/dhu

## Security / limitations

- `HostValidator.ALLOW_ALL_HOSTS_VALIDATOR` is **debug only**. Release builds use official signed host allowlist.
- The static sample content requires no fine location, microphone, storage, network or account permissions.
- This PoC must not be submitted to Play for category approval until its mock data is replaced with genuine driving-relevant POIs and TASK-003 requirements are assessed.
- This repository has no Android SDK available during generation: successful compile, DHU visibility and Play review are **unverified** until CI/device results are recorded.
- `applicationId = it.bsoft.bauto.poi.poc` is a provisional isolated identifier, not the product release package.

## Sources

- https://developer.android.com/codelabs/car-app-library-fundamentals
- https://developer.android.com/training/cars/apps/poi
- https://developer.android.com/docs/quality-guidelines/car-app-quality
