# TASK-003 — POI Play quality checklist

**Date:** 2026-10-11. **Status:** policy checklist prepared; no build or Play review has yet passed.

## Mandatory checkpoints

| ID | Verification | Status | Evidence required |
|---|---|---|---|
| PC-1 | Entire Android Auto functionality belongs to allowed category | REVIEW REQUIRED | UX walkthrough, screenshots, package |
| PF-1 | POIs correspond to real, useful destinations | BLOCKED: prototype has demo data | Real place data + user journeys |
| Car service | Manifest POI category | IMPLEMENTED, UNBUILT | merged Manifest inspection |
| Map | `MAP_TEMPLATES` permission | IMPLEMENTED, UNBUILT | Manifest + DHU render |
| Host trust | No allow-all validator in release | IMPLEMENTED, UNBUILT | source / release variant check |
| VI-1 | Phone handoff only while safely stopped | N/A in PoC, re-evaluate on voice | permission flow |
| AC-1 | <=5 templates/task | DESIGNED 2 | DHU navigation log |
| SA-1 | No animated graphical elements | DESIGNED PASS | DHU visuals |
| ST-1 | No auto-scrolling text | DESIGNED PASS | DHU visuals |
| DR-1 | Action response <=2 seconds | NOT RUN | timing log |
| DR-2 | Launch <=10 seconds | NOT RUN | cold start timing |
| DR-3 | Content <=10 seconds | NOT RUN | timing log |
| VD-1 | Icons/contrast legible | NOT RUN | light/dark screenshot |
| EP-1 | Description accurately matches behavior | PENDING listing | Play draft metadata |
| Review access | Sample real data accessible to reviewer | PENDING | test account/mock location |
| Formal review | Closed testing Play car review | NOT SUBMITTED | Play review outcome |

## Explicit NO-GO until clarified

- Generic voice-inbox button as first-class Android Auto POI control.
- Display of arbitrary non-geolocated tasks/projects.
- Upload of the current static/synthetic-location PoC as proof of PF-1 compliance.

## Gate

Technical GO != publication GO. Mark Google Play eligibility validated only after reviewer evidence for the actual experience.
