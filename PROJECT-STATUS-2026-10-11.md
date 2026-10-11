# BAuto — Stato progetto al 2026-10-11

**Milestone:** M0 Research & Feasibility (ACTIVE)  
**Source of truth:** GitHub, branch `main` after merge of feature branch.  
**Project state is tracked with evidence, not estimates.**

## Tasks

- **TASK-001** DONE — Android Auto capability research.
- **TASK-002** IN PROGRESS — voice research done; car-microphone/runtime PoCs pending.
- **TASK-003** DONE (documentary research only) — car policy, category, Google Play checks; real eligibility review pending (POC-AA-004).
- **TASK-004..010** TODO.
- **POC-AA-001** source implemented on feature branch, **build/unit tests/DHU not verified**.
- **ADR-001..005** Proposed. None Accepted.

## Key finding

A location-bound Nearby Tasks POI surface is technically supported by Car App Library, but **the generic BAuto memo/AI assistant is not automatically allowed as a POI feature**. Formal distribution eligibility is unresolved.

## Implemented PoC

[poc/android-auto-poi](poc/android-auto-poi/): Kotlin/Gradle Android Auto projection, host-rendered map/list, demo POIs, two-screen detail flow and refresh. Demo locations are fictional; no microphone, location, LLM, account, cloud or production business logic.

## Current gates

1. GitHub Actions Gradle build, tests and lint: not yet run/verified.
2. Android Auto DHU and physical device tests: not yet run.
3. Replace demo POIs with genuine real-data POI scenarios.
4. Google Play closed-testing car review: not submitted.
5. Voice POC-VOICE-001: not implemented or tested.

## References

- [Task index](agentic/tasks/INDEX.md)
- [TASK-003 policy research](agentic/research/findings/policy-distribution.md)
- [TASK-003 checklist](agentic/reports/TASK-003-POLICY-CHECKLIST.md)
- [POC-AA-001 test plan](agentic/reports/POC-AA-001-TEST-PLAN.md)
- [PoC implementation](poc/android-auto-poi/)
