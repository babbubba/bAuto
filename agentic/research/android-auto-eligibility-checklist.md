# Android Auto POI — Eligibility & Car App Quality Checklist

Verified: 2026-10-05
Task: TASK-001
Target: Android Auto / POI candidate

## Executive assessment

The principal BAuto risk is PC-1/PF-1 rather than API availability.

Google currently requires:
- PC-1: no features outside car-intended app types.
- PF-1 for POI: meaningful functionality relevant to driving.
- driving-category apps must satisfy all applicable Tier 2 / Car optimized requirements for Play acceptance.

BAuto should therefore expose a deliberately narrow car experience. Nearby Tasks is the strongest POI-shaped capability; generic knowledge/project management remains phone-side unless later evidence establishes another permitted category.

## Checklist

| Criterion | BAuto preliminary status | Design consequence / validation |
|---|---|---|
| PC-1 Permitted Categories | RISK | Car UI must not become a generic personal assistant/project manager. Validate product semantics through early Play closed-test review. |
| PF-1 POI functionality | POC REQUIRED | Nearby Tasks must return real places/actions meaningful during a drive, not merely arbitrary tasks decorated with coordinates. |
| EP-1 Expected Performance | PASS POTENTIAL | Store description and actual car experience must match exactly. |
| EP-2 Restore state | PASS POTENTIAL | Persist current car task/search/session and restore reasonably after relaunch. |
| SA-1 No screen animation | PASS POTENTIAL | Use host templates; no decorative animation/video. |
| AD-1 Advertising | PASS POTENTIAL | MVP should contain no car-display advertising. |
| IU-1 Image usage | PASS POTENTIAL | Images only where they aid driving decisions; prefer icons/host UI. |
| VI-1 Phone visual info | PASS POTENTIAL | Permission/setup handoff must tell user to use phone only when safe. |
| AC-1 <=5 screens/task | PASS POTENTIAL | Target 1–3 screens per flow. Voice capture ideally one screen/action. |
| ST-1 No auto-scrolling text | PASS POTENTIAL | No marquees/automatic text scroll. |
| DR-1 <=2s button response | POC REQUIRED | UI acknowledges immediately; AI work must not block action feedback. |
| DR-2 launch <=10s | POC REQUIRED | Measure cold/warm start in DHU and physical phone. |
| DR-3 content <=10s | POC REQUIRED | Cached/local shell; network/LLM degradation path. |
| VD-1 contrast | PASS POTENTIAL | Prefer standard Car App Library components. |
| TH-1 light/dark custom theme | N/A for 1.7/1.8; future constraint | Applies when using custom theming with CAL 1.9+. |
| PA-1 payments | N/A MVP | Keep purchases/payment off car surface. |
| VC-1 voice commands | N/A for POI | Current criterion applies to Media/Navigation, not POI. |

## Release strategy finding

Google Play review behavior is unusually useful for an early feasibility gate:
- Internal sharing: no car review.
- Internal testing: no car review.
- Closed testing: review is non-blocking.
- Open testing: blocking review.
- Production: blocking review.

Therefore POC-AA-004 should use a **closed testing track**, not internal testing, when the goal is to obtain an actual compliance signal without blocking development.

If BAuto requires sign-in, reviewer credentials must be provided. If a POI app is geographically unavailable in the US, Google requires allowing mock GPS so reviewers can test it.

## Go/no-go condition for POI

GO candidate if a minimal Nearby Tasks experience:
1. discovers or presents real actionable places;
2. is useful while driving;
3. completes common tasks in <=5 screens;
4. keeps generic memo/project administration off the car surface;
5. receives no category rejection in an early closed-track review.

NO-GO / redesign if Google considers the central experience a generic assistant rather than POI despite the above.
