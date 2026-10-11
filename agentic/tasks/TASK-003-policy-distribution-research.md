# TASK-003 — Policy & distribution research

**Status:** DONE (research gate)  
**Milestone:** M0 — Research & Feasibility  
**Date:** 2026-10-11

## Objective

Determine normative Android Auto category, quality and Play distribution constraints for BAuto before significant feature engineering.

## Deliverables

- [x] Official category and POI policy analysis: [finding](../research/findings/policy-distribution.md).
- [x] PC-1/PF-1 and release checklist: [checklist](../reports/TASK-003-POLICY-CHECKLIST.md).
- [x] Closed vs internal track review strategy.
- [x] Android 16/API 36 target requirement.
- [x] Risk register + mitigation for each blocker.
- [x] Early Play submission gate in PoC backlog.
- [ ] Google Play real review: tracked as **POC-AA-004**, not falsely reported complete under research.

## Conclusion

Official documentation supports POI category and host-rendered map/list, but BAuto Nearby Tasks eligibility remains a review hypothesis, not a guaranteed acceptance.

The general voice-note assistant functionality is **not** demonstrated to fit POI. Its CarAudioRecord technical support must remain separate from permitted app semantics.

**Research DONE.** Formal distribution verification **pending**.
