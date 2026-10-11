# TASK-002 — Voice capability matrix

**Verifica documentale:** 2026-10-11  
**Validazione eseguita:** nessuna (PoC pending). I risultati seguenti non equivalgono a test su DHU o veicolo.

| Entry point / Requisito | Compatibilità documentale | Surface | Fiducia | Prova richiesta |
|---|---|---|---|---|
| Pulsante BAuto → `CarAudioRecord` | Supportato a livello API | Android Auto car screen ammessa | Alta (API), non verificata (BAuto) | V001–V004, V011 |
| Assistant → memo BAuto personalizzato | Nessun BII memo documentato per App Actions for Cars | Android Auto | Alta | V006 per comportamento osservato |
| Assistant → POI parcheggio/ricarica | BII disponibili se funzionalità autentica | Android Auto / POI | Alta | TASK-003, eventuale PoC |
| Assistant → apertura BAuto | Lancio mobile non equivalente a car entrypoint | Phone vs AA | Media | V005, V008 |
| App Actions mobile `OPEN_APP_FEATURE` | Documentato per app mobile | Phone | Alta (API) | V007–V008 |
| Pulsante assistente del volante → BAuto | Nessuna API di intercettazione POI nota | Car | Media | V005/V006, niente dipendenza MVP |
| `VoiceInteractionService` / hotword | Possibile ruolo Assist, hotword dipendente da implementazione e piattaforma | Phone/system | Bassa | V010 |
| FGS microphone in background | Severamente limitato; eccezioni enumerate da Android | Phone | Alta (vincoli) | V009 |
| Pipeline STT + LLM | Architettura candidata, indipendente dal trigger | Phone runtime | Media | TASK-005, TASK-006, POC-AA-003 |
| Memo vocale nella categoria POI | Non automaticamente consentito dalla disponibilità di microfono | Play/Android Auto | Bassa | TASK-003 + review |

## Decisione corrente

- **One-touch:** baseline sperimentale per il MVP (ADR-004 Proposed).
- **Zero-touch:** ricerca separata, senza promessa funzionale.
- **Distribuzione:** gate indipendente; nessuna approvazione Play deducibile dalle API.

Riferimenti: [finding e fonti primarie](../research/findings/voice-assistant.md), [piano operativo](TASK-002-VALIDATION-PLAN.md).
