# ADR-004 — One-touch voice entrypoint as MVP baseline

Status: **Proposed**  
Date: 2026-10-11

## Context
Car App Library provides `CarAudioRecord`, while App Actions for Cars currently supports only parking/charging BIIs for POI apps. Background microphone and custom hotword mechanisms have substantial Android and distribution constraints.

## Proposed decision
For the first Android Auto PoC, initiate voice capture through an explicit action on a category-compliant BAuto car screen. Process speech via phone-owned STT/LLM services. Do not depend on Google Assistant or a custom hotword for the MVP.

## Consequences
- One-tap goal is technically testable.
- Zero-touch remains research/PoC work, not a promised feature.
- The legality of the voice action inside a POI experience still needs category validation.
- Android phone-only voice launch can be investigated independently.

## Acceptance gate
Accept only after car microphone PoC and car-category eligibility validation.
