# Car App Library — 1.7 stable vs 1.8 RC vs 1.9 alpha radar

Verified: 2026-10-05

## Current versions
- Stable: 1.7.0 (2026-09-09)
- Release candidate: 1.8.0-rc01 (2026-08-26)
- Alpha radar: 1.9.0-alpha02 (2026-09-09)

## 1.8 RC
Google states that 1.8.0-rc01 has the same features as 1.8.0-beta01 and includes a security fix, recommending users of lower versions update.

1.8 features:
- Sectioned Item Template.
- Enhanced Media Playback.
- IMAGE_TYPE_MEDIUM in RowImageType.
- ACTION_MEDIA_SHOW_PLAYBACK_VIEW.

### BAuto impact
Sectioned Item Template is the only currently identified 1.8 feature with clear general BAuto relevance: it could structure Nearby Tasks into contextual sections without additional navigation. The media changes are not core. No 1.8 release-note evidence indicates new hotword, Assistant, microphone, POI category, or background-listening capability.

## 1.9 radar
1.9.0-alpha02 adds:
- CarAppThemeSource / brand theming;
- SearchHeader for SectionedItemTemplate on API 9;
- component styling;
- header/layout refinements.

1.9.0-alpha01 also introduced richer presentation such as Spotlight Section and Condensed Items.

### BAuto impact
SearchHeader + SectionedItemTemplate may eventually improve the Nearby Tasks/search surface. These are UX improvements, not a solution to BAuto's eligibility or zero-touch activation problem.

## Provisional baseline decision
Do not accept an ADR selecting 1.7 merely because it is stable. The 1.8 RC contains a security fix and Google explicitly recommends upgrading from lower versions. For the first PoC, test 1.8.0-rc01 alongside 1.7.0; select the baseline after build/DHU compatibility testing.

1.9 remains technology radar only.
