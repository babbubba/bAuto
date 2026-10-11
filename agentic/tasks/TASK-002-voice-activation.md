# TASK-002 — Voice activation / Gemini / Assistant / zero-touch

**Status:** IN PROGRESS (research findings added; PoCs not executed)  
**Updated:** 2026-10-11

## Scope
- Car App Actions / BII / deep links
- Mobile App Actions and distinction from car fulfillment
- Gemini/Assistant and steering-wheel voice control
- `CarAudioRecord` one-touch capture
- Foreground microphone service and Android background restrictions
- Hotword / `VoiceInteractionService` feasibility
- Lock screen, Android Auto connected and driving-state behavior

## Deliverables created
- `research/findings/voice-assistant.md`
- `reports/TASK-002-VOICE-CAPABILITY-MATRIX.md`
- `architecture/adr/ADR-004-voice-entrypoint.md` (Proposed)

## Remaining acceptance work
- Validate trigger behavior on physical phone + Android Auto/DHU.
- Test Android 13/14/15/16 lockscreen and FGS scenarios.
- Examine alternative assistant role and its device/OEM restrictions.
- Record objective measurements and logs for PoCs.
- Update eligibility impact and promote/reject ADR-004.

Do not mark DONE without empirical evidence for runtime-dependent claims.
