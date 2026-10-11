# BAuto — M0 Task Index

**Ultimo aggiornamento:** 2026-10-11. Stato basato su evidenze, non sulla sola fattibilità teorica.

| ID | Titolo | Stato | Priorità | Dipendenze |
|---|---|---|---|---|
| TASK-001 | Android Auto capability research | **DONE (ricerca)** | P0 | — |
| TASK-002 | Voice activation & Assistant integration | **IN PROGRESS (PoC pending)** | P0 | — |
| TASK-003 | Android Auto / Play policy & distribution research | **DONE (ricerca, review pending)** | P0 | TASK-001 |
| TASK-004 | Comparable apps & patterns | TODO | P1 | — |
| TASK-005 | Speech/audio/STT | TODO | P0 | TASK-001 |
| TASK-006 | LLM providers/local/cloud | TODO | P1 | — |
| TASK-007 | Privacy/security/location/offline | TODO | P0 | TASK-001,005,006 |
| TASK-008 | Driving-safe UX | TODO | P0 | TASK-001,002 |
| TASK-009 | Nearby Tasks/POI/route context | TODO | P0 | TASK-001,003 |
| TASK-010 | Feasibility synthesis & PoC backlog | TODO | P0 | TASK-001..009 |

## Implementative feasibility gates

| PoC | Scope | Stato |
|---|---|---|
| [POC-AA-001](POC-AA-001-poi-prototype.md) | Minimal host-rendered POI | IMPLEMENTED, BUILD/DHU UNVERIFIED |
| POC-AA-004 | Google Play closed-track eligibility | NOT STARTED — needs real POIs |
| [POC-VOICE-001](POC-VOICE-001-car-microphone.md) | Car microphone, one touch | READY, NOT IMPLEMENTED |

## References

- [TASK-002](TASK-002-voice-activation.md) / [voice test protocol](../reports/TASK-002-VALIDATION-PLAN.md)
- [TASK-003](TASK-003-policy-distribution-research.md) / [policy finding](../research/findings/policy-distribution.md) / [checklist](../reports/TASK-003-POLICY-CHECKLIST.md)
- [Android POI PoC source](../../poc/android-auto-poi/)
- [POC-AA-001 test plan](../reports/POC-AA-001-TEST-PLAN.md)

**Historical task stubs:** legacy TASK-001 and TASK-002 duplicates remain for audit; use the canonical short-named files for active state.
