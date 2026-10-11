# BAuto — Master Plan

## M0 — Research & Feasibility

Goal: verificare il prodotto rispetto ad API, policy e UX Android Auto prima di architettura e MVP.

### Task documentali

1. Android Auto platform capabilities (TASK-001: DONE).
2. Voice/Gemini/Assistant (TASK-002: IN PROGRESS; PoCs pending).
3. Policy/categories/Play distribution (TASK-003: DONE research, review pending).
4. Comparable applications and patterns.
5. Audio/STT pipeline.
6. LLM provider abstraction.
7. Privacy/security/location/offline.
8. Driving-safe UX.
9. Nearby Tasks real POI and route data.
10. Consolidation and go/no-go.

### Incremental PoC gates

- **G0: Android Auto host surface** — POC-AA-001 builds, DHU list/map, refresh and details succeed.
- **G1: real POI semantics** — user errands anchored to genuine usable places, journeys and location accuracy.
- **G2: car audio** — POC-VOICE-001 technical microphone, audio focus, lifecycle behavior; separate from category eligibility.
- **G3: Play closed-track eligibility** — formal car-category review on honest product experience (POC-AA-004).
- **G4: M0 synthesis** — requirements, capability matrix, risks, evidence, architecture go/no-go.

Passing a technical gate never implies passing Play policy. Failed G3 requires an explicit product-scope alternative.

### M0 exit criteria

Each critical capability has primary sources and recorded version/date; unresolved behavior has device/host tests; risks and workarounds are catalogued; formal release strategy and review risks are grounded.

## M1 — Product & Domain Definition

Only after M0: task/memo/project domain and real-data integration.

## M2 — Architecture & Technical Baseline

Only after M0/M1: phone storage, LLM/STT provider architecture, clean boundaries.

## M3 — MVP

Depends on G0..G4. Avoid premature general voice assistant feature on car screen.

## Current links

- [M0 index](../tasks/INDEX.md)
- [TASK-003 official policy finding](../research/findings/policy-distribution.md)
- [POC-AA-001](../tasks/POC-AA-001-poi-prototype.md)
- [Test plan](../reports/POC-AA-001-TEST-PLAN.md)
