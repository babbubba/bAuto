# POC-VOICE-001 — Android Auto car microphone (one-tap)

**Status:** READY — non implementato  
**Updated:** 2026-10-11  
**Parent task:** [TASK-002](TASK-002-voice-activation.md)  
**References:** [Validation plan](../reports/TASK-002-VALIDATION-PLAN.md), [ADR-004](../architecture/adr/ADR-004-voice-entrypoint.md).

## Goal

Costruire il più piccolo PoC Android in grado di registrare input PCM dal microfono del veicolo tramite `CarAudioRecord` all'azione esplicita dell'utente, con gestione completa del ciclo di vita, degli errori e dell'audio focus.

## Non-goal

- Nessun provider STT/LLM, classificazione o storage definitivo.
- Nessuna hotword, ascolto permanente, modifica ruolo Assist.
- Nessuna dichiarazione di conformità POI/Google Play senza verifica.
- Nessun avvio segreto della registrazione.

## Work packages

### P1 — Project skeleton

- Creare app Android minimale in cartella `poc/android-auto-voice/` (progetto Kotlin/Gradle).
- Pin delle versioni SDK, plugin Gradle, Car App Library e toolchain; registrare versione esatta in README.
- Definire `CarAppService`, `Session`, `Screen` e template host-rendered ammesso.
- Isolare prototipo da ogni package di produzione.
- Dichiarare categoria POI solo se il contenuto dimostrativo è davvero POI; usare UI place-bound genuina o sandbox di test, senza bypassare policy.

### P2 — Permission preflight

- `RECORD_AUDIO` nel Manifest e richiesta runtime sul telefono.
- Preflight esplicito di permission e messaggio su schermo auto che rinvia alla configurazione sul telefono **solo da fermi**.
- Stati: `NotGranted`, `Ready`, `Recording`, `Stopping`, `Failed`, `Completed`.

### P3 — Capture

- Componente `CarVoiceCapture` isolato, senza dipendenze UI e con stato testabile.
- Ottenere audio focus per registrazione, interrompere su focus loss.
- `CarAudioRecord.create(carContext)`, avvio, lettura stream PCM (16kHz, 16-bit) su worker non-UI con cancellazione.
- Gestire lettura `-1`, stop manuale, disconnessione host, timeout e errore I/O.
- Cleanup idempotente in `finally`: stop recorder e rilascio audio focus.
- Il buffer/audio grezzo deve essere conservato solo su richiesta esplicita nel PoC e cancellato a fine prova.

### P4 — UX di prova

- Comando microfono unico, stato visuale sintetico e stop esplicito.
- Feedback immediato al tap; nessuna attesa rete o LLM.
- Nessuna navigazione oltre i template consentiti.
- Non esporre comandi di registrazione da una schermata che non rientra nella categoria app verificata.

### P5 — Unit/integration validation

- Test dei passaggi di stato e del cleanup idempotente.
- Mock di audio focus e input per simulare errore, end-of-stream e interruzione.
- DHU: V001–V004 del protocollo TASK-002, logcat e metriche.
- Veicolo reale da fermo: V011.
- Documentare eventuali differenze API/car host, incluso il comportamento del permesso.
- Test di eligibility (V012) separato e coordinato con TASK-003.

## Acceptance criteria

- Build debug riproducibile, lint/test verdi.
- Avvio/stop ripetibile senza crash.
- Evidenza PCM valido dal microfono auto (non dal microfono smartphone per errore).
- Perdita focus e disconnessione host rilasciano sempre recorder e focus.
- Nessuna registrazione automatica o nascosta.
- DHU e host reale riportati distintamente con PASS/FAIL/NOT_RUN.
- Nessuna promozione di ADR-004 ad Accepted prima dei gate di eligibility.

## Reporting

Salvare risultati secondo `reports/TASK-002-VALIDATION-PLAN.md` e aggiornare task INDEX solo dopo prove verificabili.
