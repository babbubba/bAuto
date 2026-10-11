# Android Auto POI — community/review evidence

Verified: 2026-10-05

## Purpose
Community evidence is used only to discover operational/review failure modes. Official documentation remains normative.

## Historical rejection case

A Stack Overflow report from 2022/2023 documents repeated Google Play rejection with the generic reason "App category not permitted" for an Automotive/POI submission. The developer reported that an older Car App Library dependency was part of the problem; discussion also occurred during the transition from PARKING/CHARGING categories to POI.

### What this proves
- Generic category-rejection messages have occurred in real submissions.
- Version/category transitions can create confusing review outcomes.
- Dependency/category declarations must be checked together.

### What this does NOT prove
- It does not show that current POI apps are generally rejected.
- It does not show that BAuto will be accepted or rejected.
- Old advice to use deprecated PARKING/CHARGING categories must not be copied into a 2026 implementation.

## Official-sample issue evidence

The official `android/car-samples` issue tracker contains historical issues around map/template behavior. This reinforces testing on the actual target host/DHU rather than assuming a template will behave identically on every environment.

## Release risk treatment

R-001 remains HIGH until an actual BAuto-shaped POI prototype receives an early Play review signal.

Mitigation:
1. use current Car App Library;
2. keep category semantics strict;
3. use official templates first;
4. test DHU;
5. submit a minimal but genuine Nearby Tasks experience early;
6. preserve review feedback as project evidence.
