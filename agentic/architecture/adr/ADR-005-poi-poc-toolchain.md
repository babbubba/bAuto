# ADR-005 — POI prototype baseline and isolated Android project

**Status:** Proposed  
**Date:** 2026-10-11  
**Decision owner:** BAuto project  
**Related:** TASK-003, POC-AA-001

## Context

Android Auto category eligibility is the principal risk. A fast isolated POI proof should not prematurely lock in domain storage, AI integration, UI framework or future app architecture.

Official release documentation (checked 2026-10-11) lists Car App Library 1.7.0 stable, 1.8.0-rc01 and 1.9.0-alpha03. AGP 8.13.2 supports Gradle 8.13, API 36 and Java 17.

## Proposal

- Isolate executable PoC in `poc/android-auto-poi/`.
- Use Android Kotlin, no Compose or custom car map.
- `androidx.car.app:app:1.7.0` for first host/DHU compatibility check.
- AGP 8.13.2, Kotlin 2.2.21, Gradle 8.13, JDK 17, compile/target SDK 36.
- Min phone SDK 28, Car App minimum API level 5.
- Show POI host-rendered `PlaceListMapTemplate` with demo content.
- Debug-only accept-all host validator; trusted host allowlist in release.
- No microphone, no location provider, no LLM and no network calls in first PoC.

## Alternatives

- 1.8 RC: security fix and sectioned-list options; retest/compare after initial baseline.
- 1.9 alpha: not suitable for MVP stability; technology radar.
- App-rendered map: unnecessary complexity until POI eligibility proven.
- Full architecture/DB: premature under unresolved distribution risk.

## Acceptance gate

Promote to Accepted only after reproducible CI build, DHU functionality and decision on compatibility/security differences between 1.7 and 1.8. A public release may require a new ADR and dependency upgrade.

## Primary references

- https://developer.android.com/jetpack/androidx/releases/car-app
- https://developer.android.com/build/releases/agp-8-13-0-release-notes
- https://developer.android.com/google/play/requirements/target-sdk
