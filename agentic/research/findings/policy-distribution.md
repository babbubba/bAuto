# TASK-003 — Android Auto policy, Play eligibility and distribution

**Verified:** 2026-10-11  
**Scope:** Android Auto projected experience (not Android Automotive OS).  
**Evidence:** Official Android documentation only. This is an interpretation of public policy, **not a Google Play approval**.

## Normative requirements

### PC-1 — Permitted Categories

The app must not provide functionality outside app types intended for cars.

- POI exists as a supported Android Auto category.
- A generic AI assistant, voice notes inbox or project/task manager is **not** documented as a standalone Android Auto category.
- Declaring `androidx.car.app.category.POI` does **not** permit displaying arbitrary note-taking features.

Source: https://developer.android.com/docs/quality-guidelines/car-app-quality

### PF-1 — Point of Interest Functionality

A POI app must provide **meaningful functionality relevant to driving**. For BAuto the strongest candidate is **Nearby Tasks**, framed as real user activities at real, discoverable places relevant to a journey.

Interpretation (requires real review):
- YES candidate: show actionable locations where the user can complete location-bound errands, plus navigational handoff.
- HIGH RISK: fake POIs, project-management UI or a memo/LLM interface dressed up as a POI list.
- UNPROVEN: an explicit generic voice note button within a POI app. `CarAudioRecord` supports audio capture but does not by itself authorize arbitrary app-category functionality.

Source: https://developer.android.com/training/cars/apps/poi

### Car App Library plumbing

- `CarAppService` with POI intent-filter category.
- `androidx.car.app.MAP_TEMPLATES` permission for `PlaceListMapTemplate`.
- `automotive_app_desc.xml` with `<uses name="template" />` and corresponding application metadata.
- `androidx.car.app.minCarApiLevel` declared.
- Production host validation must use trusted host certificates, not unconditional accept-all.
- No unrelated service categories (Media/Navigation/IOT) unless their genuine functionality exists.

Sources:
- https://developer.android.com/training/cars/apps/library/set-up-project
- https://developer.android.com/training/cars/apps/poi
- https://developer.android.com/reference/androidx/car/app/CarAppService

### Driver-safety / quality

Key POI quality controls:

| Criterion | Requirement | PoC action |
|---|---|---|
| PC-1 | Only car-intended feature types | Only POI experience in car |
| PF-1 | Meaningful driving-relevant POI functionality | Review semantic eligibility; real data needed for Play |
| VI-1 | Tell driver to access phone only when safe | Permission/setup handoff message |
| AC-1 | Complete tasks in at most five screens | List -> details -> back, 2 screens |
| SA-1 | No animated UI | Host-rendered template, static icons |
| ST-1 | No auto-scrolling text | No marquees |
| DR-1 | Buttons respond in <= 2 sec | UI callback synchronous; measure |
| DR-2 | Launch in <= 10 sec | Measure cold/warm DHU |
| DR-3 | Content in <= 10 sec | Static in-memory content; measure |
| VD-1 | Contrast compliant | Host-rendered components |
| AD-1 | No ads / compliant advertising where relevant | No ads |
| TH-1 | Custom theming 1.9+ requires light/dark | No custom theme |

Full category-specific requirements must be reviewed again before Play submission.
Source: https://developer.android.com/docs/quality-guidelines/car-app-quality

### Testing and Google Play

| Track | Form-factor review |
|---|---|
| Internal sharing | None |
| Internal testing | None |
| Closed testing | **Non-blocking** |
| Open testing | Blocking |
| Production | Blocking |

Use DHU + physical host tests first. For an early formal policy signal, upload a **genuinely functional** POI build to closed testing. Synthetic placeholder errands alone do not provide an honest eligibility test.

- Do not prototype car support inside an already-live production APK.
- If sign-in is required, provide review credentials.
- If POI is not usable in the US, support mock GPS for reviewers.
- Track review rejection reasons and resubmit; approval of an arbitrary example is not proof BAuto's eventual generic assistant is admissible.

Source: https://developer.android.com/training/cars/distribute

### API target level

Starting August 31, 2026, new Android phone applications and updates submitted to Play generally require **target SDK 36**; Android Automotive OS has a separate exception. BAuto projected Android Auto is a phone application, so PoC chooses `targetSdk = 36`.

Source: https://developer.android.com/google/play/requirements/target-sdk

## Risks and decisions

| ID | Severity | Risk | Mitigation | Closure |
|---|---|---|---|---|
| R-001 | Critical | POI classification may be rejected for Nearby Tasks | True POI UX + early closed-track review | Google Play review |
| R-002 | High | Generic voice notes not POI | Keep out of first car PoC | Independent product/policy decision |
| R-003 | High | A demo with fictional destinations masquerades as production app | Label sample data DEMO; no Play upload until real data | Real POI data |
| R-004 | Medium | Host/API/library incompatibility | Test DHU, physical host, version matrix | Runtime evidence |
| R-005 | Medium | Delay > quality limits | Instrument cold start, content, touch latency | Measured evidence |
| R-006 | Medium | Unknown source / host validation security | Signed allowlist release, debug-only ALLOW_ALL | Audit release build |
| R-007 | Medium | Wrong target SDK/release submission requirements | Target 36, recheck before uploading | Build/release validation |
| R-008 | Medium | Play review cannot exercise app | Review credentials, test location, reachable content | Play checklist |

## Research conclusion

**TASK-003 policy research: COMPLETE (documentation gate).**  
**BAuto publication eligibility: NOT YET ESTABLISHED.**

Next: POC-AA-001 (technical test), real user POI dataset, then POC-AA-004 Play closed-test policy validation. ADR-002 remains Proposed; do not claim acceptance.

## Source index

- https://developer.android.com/docs/quality-guidelines/car-app-quality
- https://developer.android.com/training/cars/apps/poi
- https://developer.android.com/training/cars/apps/library/set-up-project
- https://developer.android.com/training/cars/distribute
- https://developer.android.com/google/play/requirements/target-sdk
- https://developer.android.com/codelabs/car-app-library-fundamentals
