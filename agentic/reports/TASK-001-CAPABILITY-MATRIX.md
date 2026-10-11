# TASK-001 final capability matrix

Verified: 2026-10-05

| Capability | Android Auto status | BAuto conclusion |
|---|---|---|
| Projected templated UI | Supported | GO |
| `CarAppService` / Session / Screen model | Supported | GO |
| POI category | Supported | GO technically; eligibility must be validated |
| Host-rendered POI map/list | Supported via `PlaceListMapTemplate` | GO |
| App-rendered map + content | Supported via `MapWithContentTemplate` | Later option |
| Refresh POI ranking/content | Supported | GO |
| Car microphone | Supported via `CarAudioRecord` | GO, permission + audio focus required |
| Own STT/processing of captured audio | Supported in principle | GO to PoC |
| Generic project/task manager on car | No matching category established | NO for current car scope |
| Nearby location-bound errands | Plausible POI semantics | GO to PoC/review |
| Arbitrary generic assistant category | Not established | NO assumption |
| One-tap voice capture | Technically feasible | GO to PoC |
| Zero-touch/custom hotword | Not established in TASK-001 | TASK-002 |
| Generic Hey Google note command | Not established | TASK-002 |
| Complex settings/API keys | Phone-side | GO |
| DHU testing | Supported | GO |
| Real-car testing | Supported via trusted distribution | GO |
| 1.8 RC advantage for voice/POI eligibility | None identified | Do not upgrade for this reason alone |
| 1.8 RC general changes/security | Exists | Compare empirically before ADR |
| 1.9 alpha | UX/radar value | Do not baseline MVP |

## TASK-001 conclusion

**Technical feasibility: positive.**
Android Auto exposes enough primitives to build a meaningful BAuto car surface and one-tap voice capture.

**Distribution/category feasibility: unresolved but testable.**
Nearby Tasks is the strongest supported-category hypothesis. It must be implemented as genuine driving-relevant POI functionality and validated early with Google Play.

**Architecture consequence:**
Do not design the Android Auto surface as the full BAuto product. Design BAuto as a phone-centric assistant with a deliberately constrained car projection.
