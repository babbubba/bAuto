# Android Auto — executable PoC backlog after TASK-001

## POC-AA-001 — Minimal genuine POI

### Goal
Validate that BAuto Nearby Tasks can be implemented as an honest POI experience.

### Implementation
- Android phone app + Car App Library module/surface.
- `CarAppService`, `Session`, `Screen`.
- category `androidx.car.app.category.POI`.
- `automotive_app_desc.xml` with `template`.
- `androidx.car.app.MAP_TEMPLATES`.
- `PlaceListMapTemplate`.
- 5–10 synthetic-but-realistically-shaped place-bound errands.
- content refresh using `setOnContentRefreshListener` + `invalidate()`.

### Acceptance
- visible in DHU;
- host map + rows render;
- refresh works;
- no flow exceeds five templates;
- no non-POI project-management UI is exposed;
- content appears within Car App Quality timing constraints.

### Evidence
Screenshots/video, test log, manifest snapshot, dependency versions.

---

## POC-AA-002 — Car microphone

### Goal
Prove one-tap capture from the car microphone.

### Implementation
- `RECORD_AUDIO`;
- runtime permission onboarding;
- microphone action;
- request audio focus;
- `CarAudioRecord.create(carContext)`;
- read PCM stream;
- stop on explicit action/end-of-speech;
- stop immediately on audio-focus loss;
- release focus.

### Acceptance
- starts reliably from DHU;
- audio stream is non-empty;
- media is not captured with user speech;
- focus loss terminates capture;
- UI acknowledges action promptly;
- permission failure has a safe phone-side recovery.

---

## POC-AA-003 — Voice round trip

### Goal
Measure `CarAudioRecord -> STT -> structured intent -> confirmation`.

### Metrics
- tap-to-record latency;
- end-of-speech detection;
- STT latency;
- classification latency;
- total confirmation latency;
- behavior with no network/provider timeout.

### Acceptance
No long UI blocking; immediate acknowledgement; deterministic failure path.

---

## POC-AA-004 — Early Play eligibility experiment

### Goal
Obtain a real Google Play car-category signal before large-scale implementation.

### Prerequisites
POC-AA-001 passes quality checklist and is a genuine usable POI experience.

### Procedure
- prepare Play car form-factor declaration;
- release candidate through the earliest track that yields the desired car review signal according to current Play documentation;
- provide reviewer credentials if required;
- ensure geographic testability/mock location if applicable;
- record exact review response and policy references.

### Acceptance
PASS: POI/car experience accepted.
FAIL: rejection captured as architectural input; do not disguise/relabel functionality without policy basis.

---

## POC-AA-005 — 1.7 vs 1.8 RC compatibility

### Goal
Select Car App Library baseline empirically.

### Matrix
Build/run POC-AA-001 and AA-002 with:
- 1.7.0 stable;
- 1.8.0-rc01.

### Compare
- compilation/API changes;
- host compatibility;
- template behavior;
- microphone behavior;
- known defects;
- security/release-note implications.

### Decision
Create ADR selecting baseline only after test evidence.
