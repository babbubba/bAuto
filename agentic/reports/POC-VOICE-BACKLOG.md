# Voice PoC backlog — runnable tasks

## POC-VOICE-001 — One-tap car microphone
Dependency: POI prototype. Implement `CarAudioRecord`, runtime permission, focus request/release, stop on focus loss. Test DHU and real vehicle. Save logs of input start/stop and microphone source.

## POC-VOICE-002 — Assistant launch while driving
Install a test BAuto build. Compare “open BAuto” and app-specific command with Android Auto connected/disconnected and phone locked/unlocked. Capture what opens on phone vs head unit. No unsafe interaction while driving; test stationary.

## POC-VOICE-003 — Mobile App Actions vs Cars
Implement mobile `OPEN_APP_FEATURE` in a throwaway package and compare with the two documented car POI BIIs. Record launch intents and whether fulfillment reaches the car `Session`.

## POC-VOICE-004 — Assistant role/hotword feasibility
Investigate `VoiceInteractionService`, role selection, supported hardware and conflict with Gemini. Test only on a dedicated device and explicitly document user consent. Do not implement always-on audio collection as a default.

## POC-VOICE-005 — Foreground microphone constraints
On Android 13/14/15/16 compare visible start, background start, notification action, widget action, locked phone and post-boot. Capture thrown exceptions, microphone availability and permission status.

## Acceptance
Each PoC requires device/OS version, Android Auto version, test steps, logs, results and decision impact.
