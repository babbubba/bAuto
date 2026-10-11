# BAuto — M0 Task Index

**Ultimo aggiornamento:** 2026-10-11. Lo stato si riferisce alla documentazione e alle prove effettivamente svolte, non alla sola fattibilità teorica.

| ID | Titolo | Stato | Priorità | Dipendenze |
|---|---|---|---|---|
| TASK-001 | Android Auto capability research | **DONE (ricerca)** | P0 | — |
| TASK-002 | Voice activation & Assistant integration | **IN PROGRESS (PoC pending)** | P0 | — |
| TASK-003 | Policy & distribution | TODO | P0 | TASK-001 |
| TASK-004 | Comparable apps & patterns | TODO | P1 | — |
| TASK-005 | Speech/audio/STT | TODO | P0 | TASK-001 |
| TASK-006 | LLM providers/local/cloud | TODO | P1 | — |
| TASK-007 | Privacy/security/location/offline | TODO | P0 | TASK-001, 005, 006 |
| TASK-008 | Driving-safe UX | TODO | P0 | TASK-001, 002 |
| TASK-009 | Nearby Tasks/POI/route context | TODO | P0 | TASK-001, 003 |
| TASK-010 | Feasibility synthesis & PoC backlog | TODO | P0 | TASK-001..009 |

## TASK-002 tracking

- [Task attivo](TASK-002-voice-activation.md)
- [Validation plan](../reports/TASK-002-VALIDATION-PLAN.md)
- [Finding](../research/findings/voice-assistant.md)
- [Capability matrix](../reports/TASK-002-VOICE-CAPABILITY-MATRIX.md)
- [ADR-004](../architecture/adr/ADR-004-voice-entrypoint.md) — Proposed

**Nota:** i vecchi file `TASK-001-android-auto-capability-research.md` e `TASK-002-voice-activation-assistant-integration-research.md` sono tracce legacy. Per lo stato usare questo indice e i task attivi con nomi brevi.
