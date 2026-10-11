# POC-AA-001 — Genuine POI proof-of-concept

**Status:** IMPLEMENTED — BUILD/DHU NOT VERIFIED  
**Updated:** 2026-10-11  
**Milestone:** M0  
**Dependencies:** TASK-001 (research), TASK-003 (policy research)

## Target

Create an Android Auto application displaying actual POI-shaped errands as a host-rendered list and map, allowing a two-screen list/detail flow and same-template refresh.

## Implementation

Project: [poc/android-auto-poi](../../poc/android-auto-poi/).

- [x] Kotlin/Gradle Android phone package for Android Auto projection.
- [x] `CarAppService`, `Session`, `Screen`.
- [x] POI category, map template permission and `minCarApiLevel=5`.
- [x] `PlaceListMapTemplate` with metadata, pins and `DistanceSpan` (distance from DEMO anchor, not vehicle).
- [x] Explicit DEMO sample POIs/errands, entirely offline/in memory.
- [x] Two-screen path to details with `Action.BACK`.
- [x] Same-template refresh toggling sort order.
- [x] Debug-only permissive host validator; release trusted-host allowlist.
- [x] Initial JUnit tests for sample data invariants.
- [x] CI workflow configuration.
- [ ] CI Gradle build/tests/lint PASS.
- [ ] App appears in Android Auto Desktop Head Unit.
- [ ] Map/list pins and refresh proven visually.
- [ ] Detailed quality tests timed and recorded.
- [ ] Real locations and actual errands replace demo dataset.
- [ ] Physical vehicle test while stationary.

## Important limitation

Sample POIs and activities are fictional and associated with approximate Torino coordinates. Do not use the prototype for navigation, product claims, or a Google Play eligibility proof. The Play test must use real meaningful POIs and compliant content.

## Acceptance

1. CI assembly + unit tests + lint pass.
2. DHU displays host-rendered POI list and map with three points.
3. Refresh updates row order without adding a template.
4. Tapping any item opens details; Back returns to list (two screens).
5. No unsupported category feature exposed, no location or microphone permission.
6. Pre-publication POI semantics review completed and recorded.

See [test plan](../reports/POC-AA-001-TEST-PLAN.md).

## Next

When CI and DHU tests pass: continue with POC-VOICE-001 (technical, while policy separate) or POC-AA-004 for real-data eligibility review.
