# TASK-002 — Piano di validazione sperimentale (Android Auto voice)

**Data:** 2026-10-11  
**Stato:** Specifica pronta; nessun test eseguito.  
**Owner:** sviluppo BAuto.

## Prerequisiti

- Android Studio e Android SDK, ADB, DHU (Desktop Head Unit), smartphone Android compatibile con Android Auto.
- Progetto PoC isolato dal pacchetto di produzione; test con veicolo fermo.
- Registrare: marca/modello dispositivo, Android build/API, versione Android Auto, Car App Library, Google App/Gemini e DHU.
- Permessi microfono ottenuti **sul telefono da fermi**; nessuna richiesta di interagire col telefono in marcia.
- Separare il test di compatibilità tecnica dalla verifica della categoria/eligibility Google Play.

## Scenari

| ID | Piattaforma | Stato | Azione | Risultato osservabile |
|---|---|---|---|---|
| V001 | Android Auto DHU | BAuto visibile | Tap sul microfono dell'app | `CarAudioRecord` crea stream PCM non vuoto |
| V002 | Android Auto DHU | Registrazione attiva | Perdita audio focus | Capture si arresta, stream rilasciato |
| V003 | Android Auto DHU | Permission denied | Avvia microfono | Fallback informativo, nessun crash |
| V004 | Android Auto DHU | Registrazione attiva | Stop/disconnessione host | Risorse chiuse, input terminato |
| V005 | Android Auto DHU | App in car | «Hey Google, apri BAuto» | Annotare destinazione (telefono/head unit), riuscita, eccezioni |
| V006 | Android Auto DHU | Telefono locked | «Hey Google, prendi una nota con BAuto» | Annotare il risultato senza assumere supporto |
| V007 | Telefono | AA scollegato | `OPEN_APP_FEATURE` configurato | Verificare deep link e fulfillment mobile |
| V008 | Telefono | AA collegato | Stesso comando di V007 | Confrontare la destinazione con V007 |
| V009 | Telefono Android 13–16 | Foreground e background | Start FGS microphone | Registrare permessi, errori e ciclo di vita |
| V010 | Dispositivo dedicato | Ruolo assistente consentito | Prova `VoiceInteractionService` | Verificare selezionabilità, invocazione, disponibilità hotword |
| V011 | AA host reale | Auto ferma | Ripetere V001–V004 | Evidenziare differenze da DHU |
| V012 | Android Auto DHU | PoC POI | Pulsante memo vocale in POI | Validazione categoria separata dai risultati tecnici |

## POC-VOICE-001: protocollo tecnico

1. Creare `CarAppService`, `Session`, `Screen` minimale (categoria solo se pertinente).
2. Manifest: `RECORD_AUDIO` e dichiarazioni Android Auto corrette; richiedere permesso runtime sul telefono.
3. Aggiungere azione microfono su `Screen`.
4. Prima del recording richiedere audio focus; su perdita focus terminare immediatamente.
5. `CarAudioRecord.create(carContext)`; `startRecording()`; leggere PCM con loop cancellabile.
6. Terminare su input `-1`, tap di stop, perdita focus, timeout, errore o distruzione schermo/sessione.
7. `stopRecording()`, chiusura dell'eventuale stream e `abandonAudioFocusRequest` anche nei percorsi di errore.
8. Riportare tempo tap→primo buffer, byte letti, stato audio focus, tempo stop, errori, compatibilità host.

La forma delle chiamate deve essere verificata sulle firme precise della libreria installata e non copiata ciecamente da questo pseudoprotocollo.

### Criteri PASS

- Su host supportato i buffer audio sono non vuoti.
- Lo stop e la perdita focus interrompono la registrazione.
- Errori/permessi negati non causano crash o registrazione inattesa.
- Nessuna cattura automatica, nascosta o permanente.

## POC-VOICE-002/003: Gemini e App Actions

- Provare frasi esatte documentando lingua/locale, connessione AA, lock/unlock e assistente attivo.
- Separare deep link mobile e car surface.
- Non usare i BII di parcheggio/ricarica per un caso d'uso memo.
- Un esito positivo su telefono non dimostra funzionamento del car host.

## POC-VOICE-004: assistente alternativo

- Solo dispositivo di test con consenso esplicito.
- Verificare il ruolo Android Assist e l'eventuale disponibilità hardware/OEM della hotword.
- Testare conflitti con Gemini/Assistant e AA. Non alterare la configurazione del telefono principale senza consenso.
- Nessuna implicazione automatica di supporto alla distribuzione Play.

## POC-VOICE-005: FGS microphone

- Prove Android 13, 14, 15, 16 **solo sulle versioni effettivamente disponibili**.
- Stato: foreground, background, locked, avvio da notifica/widget, boot, eccezioni autorizzate.
- Registrare `SecurityException`, `ForegroundServiceStartNotAllowedException`, permessi concessi e targetSdk.
- La riuscita con un'eccezione documentata non consente di generalizzare a qualsiasi avvio background.

## Evidenze da salvare

Creare `agentic/reports/evidence/TASK-002/<scenario-id>/` con:
- `environment.md` (versioni e dispositivo);
- `steps.md` (procedura e aspettativa);
- `result.md` (PASS/FAIL/BLOCKED/NOT_RUN e osservazioni);
- `logcat.txt` con eventuali informazioni personali oscurate;
- screenshot/video se necessari e privacy-preserving.

## Gate decisionale

- **GO one-touch (tecnico):** V001–V004 PASS su DHU e almeno V011 su host fisico.
- **GO zero-touch:** solo con entry point robusto, autorizzato e riproducibile; altrimenti RESEARCH ONLY.
- **GO distribuzione:** TASK-003 + review Play, separatamente da questo gate.
- ADR-004 resta **Proposed** fino a completamento delle evidenze richieste.
