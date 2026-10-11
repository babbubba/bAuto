# ADR-004 — One-touch voice entrypoint as MVP baseline

**Status: Proposed**  
**Date:** 2026-10-11  
**Related:** TASK-002, TASK-003, POC-VOICE-001

## Context

`CarAudioRecord` è un'API di acquisizione microfono in Android Auto, mentre App Actions for Cars non espone un BII generico di creazione memo. Background microphone e hotword personalizzata hanno restrizioni tecniche e di autorizzazione. Nessuna categoria Android Auto generica di personal assistant è stata confermata per BAuto.

## Proposed decision

- Per il primo PoC usare attivazione *esplicita one-touch* su una car screen conforme alla categoria effettivamente autorizzata.
- Acquisire audio tramite `CarAudioRecord`, con permessi e audio focus.
- STT, classificazione LLM, storage e configurazione restano nel runtime dell'app sul telefono.
- Non dipendere da Gemini/Google Assistant, hotword BAuto o pulsante vocale del volante per la prima release.
- Zero-touch resta una capability candidata da ricercare e validare autonomamente.

## Alternatives

1. App Actions mobile → deep link nel telefono: da verificare, non garantisce Android Auto.
2. Custom assistant `VoiceInteractionService`: alta complessità, comportamento/OEM da provare.
3. Foreground service microphone: limiti di avvio background e uso microfono, non equivale a hotword.
4. Solo interfaccia telefono: fallback condizionato da sicurezza e regole Android/Play.

## Consequences

- Percorso più semplice per iniziare test tecnici e misurare latenza.
- Rischio categoria/eligibility resta aperto: un pulsante per memo generici non diventa automaticamente una funzionalità POI.
- App deve gestire errori, permessi, privacy, focus loss e interruzioni host senza richiedere interazioni pericolose alla guida.

## Acceptance gate

**Non accettare ADR** fino a:
- successo documentato di POC-VOICE-001 su DHU e veicolo reale;
- valutazione del comportamento voice trigger / Assistant nei test rilevanti;
- conferma di eligibility della superficie auto tramite TASK-003/review;
- definizione del fallback per host o permessi non supportati.

Tracking: [TASK-002](../../tasks/TASK-002-voice-activation.md), [Validation plan](../../reports/TASK-002-VALIDATION-PLAN.md).

Fonti: https://developer.android.com/develop/devices/assistant/cars ; https://developer.android.com/training/cars/apps/library/car-microphone .
