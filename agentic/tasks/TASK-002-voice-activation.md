# TASK-002 — Voice activation / Gemini / Assistant / zero-touch

**Status:** IN PROGRESS — analisi API completata, validazione runtime pendente  
**Updated:** 2026-10-11  
**Milestone:** M0 — Research & Feasibility  
**Priority:** P0

## Objective

Determinare i punti di ingresso vocali realmente utilizzabili da BAuto su Android Auto e telefono, separando compatibilità documentata, fattibilità su dispositivo e distribuzione Play.

## Findings verificati (fonti ufficiali)

- `CarAudioRecord`: stream audio dal microfono auto, permesso runtime `RECORD_AUDIO`; audio focus necessario e registrazione da interrompere alla perdita del focus.
- App Actions for Cars: Car App Library + categoria POI; fulfillment via deep link; **solo** `GET_PARKING_FACILITY` e `GET_CHARGING_STATION` tra i BII documentati. Nessuna integrazione generica «crea memo» autorizzata da tale documentazione.
- Mobile App Actions: `OPEN_APP_FEATURE`, `GET_THING` e custom intents si riferiscono alle app Android del telefono. Non equivalgono a un punto di ingresso Android Auto.
- `VoiceInteractionService`: ruolo assistente di sistema selezionato dall'utente, con capacità potenziali di hotword. Non dimostra disponibilità di hotword personalizzata o sostituzione di Gemini nell'auto.
- Android 12+: limiti di lancio FGS dal background; Android 14+: controlli while-in-use per FGS microphone alla creazione, con eccezioni esplicite.

Fonti nel finding: [voice-assistant.md](../research/findings/voice-assistant.md).

## Deliverable

- [x] Analisi delle API e dei limiti ufficiali.
- [x] Matrice capability documentata.
- [x] ADR-004 Proposed (one-touch MVP).
- [x] Protocollo di verifica riproducibile [TASK-002-VALIDATION-PLAN.md](../reports/TASK-002-VALIDATION-PLAN.md).
- [x] Distinzione tra attivazione tramite Assistant telefonico e Car App Actions.
- [ ] POC-VOICE-001: microfono BAuto, DHU e veicolo reale.
- [ ] POC-VOICE-002: invocazione Assistant/Gemini con AA collegato e telefono bloccato.
- [ ] POC-VOICE-003: App Actions mobile vs car, lingue supportate.
- [ ] POC-VOICE-004: assistente alternativo, ruolo Android e hotword (solo dispositivo dedicato).
- [ ] POC-VOICE-005: FGS microphone Android 13–16, matrici lockscreen/background.
- [ ] Valutazione della legittimità della funzione «memo vocale» nella categoria auto effettiva (dipende da TASK-003).
- [ ] Risultati dei PoC, log, versione host/OS, evidenze e pass/fail.
- [ ] Approvazione o rigetto di ADR-004.

## Acceptance criteria

1. Matrice `trigger × surface × lock state × Android version × host` compilata con prove ripetibili.
2. Fallimenti dei trigger vocali documentati senza inferire un'impossibilità assoluta da un singolo dispositivo.
3. Percorso one-touch dimostrato con `CarAudioRecord`, controllo audio focus, permessi e gestione dell'errore.
4. Modalità zero-touch promossa solo dopo prova positiva e verifica policy.
5. Decisione finale su ADR-004 esplicita; non promuovere ad Accepted automaticamente.
6. Nessun PoC o build dichiarato eseguito senza log e artefatti.

## Current assessment

**Research conclusion:** one-touch è l'opzione tecnicamente più supportata per il prototipo.  
**Runtime conclusion:** non verificata.  
**Distribution conclusion:** ancora da validare con TASK-003.  
**Next action:** implementare POC-VOICE-001 e produrre evidenza DHU, quindi POC-VOICE-002/003. I test in automobile si eseguono da fermi.

## References

- https://developer.android.com/develop/devices/assistant/cars
- https://developer.android.com/training/cars/apps/library/car-microphone
- https://developer.android.com/training/cars/testing/dhu
- https://developer.android.com/develop/background-work/services/fgs/restrictions-bg-start
- https://developer.android.com/develop/background-work/services/fgs/service-types
- https://developer.android.com/reference/android/service/voice/VoiceInteractionService
