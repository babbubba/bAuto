# TASK-002 — Voice activation, Gemini, Assistant, zero-touch

**Verifica fonti:** 2026-10-11. **Stato:** ricerca documentale iniziale completata; verifiche su dispositivo/auto ancora da eseguire. **TASK-002: IN PROGRESS**.

## Conclusione

Il percorso più solido per BAuto è **un'azione esplicita nella sua interfaccia Android Auto** che acquisisca il microfono tramite `CarAudioRecord`. La presenza dell'API **non implica** che una funzione generica di memo/assistente sia autorizzata in una app POI: resta valido il gate di eligibility del TASK-001.

L'invocazione con `Hey Google` di una **funzione arbitraria di BAuto sul display auto** non è attualmente documentata dalle App Actions for Cars: supportano POI e solo i BII `GET_PARKING_FACILITY` e `GET_CHARGING_STATION`. Le App Actions **generiche del telefono** includono `OPEN_APP_FEATURE`, `GET_THING` e custom intents, ma **non vanno confuse** con la compatibilità Android Auto. Una possibile invocazione telefonica è una pista sperimentale, non una garanzia di esecuzione in auto con telefono bloccato.

Il **pulsante vocale del volante** e l'icona microfono di sistema attivano Gemini/Assistant; non esiste nella documentazione consultata un'API pubblica che consenta a una normale app POI di intercettare e riassegnare liberamente quel pulsante.

Una **hotword personalizzata** richiederebbe un'analisi distinta dell'eventuale ruolo di assistente Android (`VoiceInteractionService`) e delle relative condizioni di sistema/utente. Non è una funzionalità ottenibile semplicemente dichiarando `RECORD_AUDIO` o avviando un servizio persistente. Non assumere la disponibilità del microfono del veicolo fuori da una sessione Car App Library attiva.

## Matrice di attivazione

| Trigger | Meccanismo | Stato | Limite / verifica |
|---|---|---|---|
| Tap sul microfono BAuto in una car screen ammessa | `CarAudioRecord`, audio focus, `RECORD_AUDIO` | **Supportato tecnicamente** | UI/categoria POI devono essere conformi; test DHU e veicolo |
| `Hey Google, cerca parcheggio con BAuto` | App Actions for Cars, `GET_PARKING_FACILITY`, deep link | **Documentato per POI** | Solo se BAuto offre realmente tale funzionalità |
| `Hey Google, cerca una stazione di ricarica con BAuto` | App Actions for Cars, `GET_CHARGING_STATION` | **Documentato per POI** | Solo funzionalità pertinente e implementata |
| `Hey Google, prendi una nota con BAuto` | App Actions for Cars | **Non supportato dai BII car documentati** | Non utilizzare BII parcheggio/ricarica per aggirare la policy |
| `Hey Google, apri BAuto` | Lancio app via Assistant | **Possibile lato telefono, non dimostrato come apertura affidabile car surface** | PoC su AA attivo/telefono bloccato |
| App Actions generiche (`OPEN_APP_FEATURE`, custom intents) | Assistant + `shortcuts.xml` + fulfillment | **Supportate per app Android mobili** | Nessuna equivalenza automatica con App Actions for Cars |
| Pulsante vocale volante | Assistente di sistema | **Supportato per Gemini/Assistant** | Non assegnabile liberamente a BAuto tramite API POI note |
| Hotword proprietaria sempre attiva | `VoiceInteractionService` / assistente predefinito | **Da studiare con PoC dedicato** | Ruolo, supporto hotword, hardware/OEM, UX, privacy, conflitto Gemini |
| Avvio microfono FGS da app in background | `foregroundServiceType=microphone` | **Generalmente limitato** | Android 12+ limita FGS in background; Android 14+ controlla while-in-use; esistono eccezioni specifiche |
| Continuazione registrazione già avviata visibilmente | Microphone FGS | **Potenzialmente consentita lato telefono** | Non equivale a hotword permanente; test su OS e lockscreen |

## Restrizioni Android FGS / microfono

- Android 12+ limita l'avvio dei foreground service dal background.
- Android 14+ verifica al momento della creazione del servizio microfono le autorizzazioni **while-in-use**; avviare un servizio microfono da background può generare `SecurityException` anche quando un controllo generico dei permessi restituisce `PERMISSION_GRANTED`.
- Il tipo di servizio `microphone` richiede `FOREGROUND_SERVICE_MICROPHONE` e `RECORD_AUDIO`, oltre alla corretta dichiarazione del service nel manifest.
- Esistono eccezioni documentate (interazione con notifiche/widget, componente di sistema, ruolo `VoiceInteractionService` e altri casi): non costituiscono un'autorizzazione generale ad ascoltare sempre.

## Pattern raccomandato per MVP

1. Onboarding su telefono: consenso microfono, provider STT/LLM, privacy.
2. Nell'interfaccia auto **solo se legittimamente ammessa**, pulsante microfono esplicito.
3. Acquisizione audio con `CarAudioRecord` e audio focus.
4. Risposta immediata alla pressione; registrazione terminabile/timeout.
5. STT/LLM e persistenza lato telefono, con conferma sintetica.
6. Se car UI non ammessa, considerare una modalità telefonica autonoma, da validare separatamente per sicurezza e policy.

## Follow-up obbligatori

- `POC-VOICE-001`: microfono one-tap su DHU e auto, permessi e audio focus.
- `POC-VOICE-002`: tentativo lancio via Assistant/Gemini con Android Auto connesso e telefono bloccato.
- `POC-VOICE-003`: verifica App Actions mobile vs Cars su dispositivo reale.
- `POC-VOICE-004`: verifica `VoiceInteractionService`/assistant role e compatibilità Android Auto; **solo sperimentale**.
- `POC-VOICE-005`: FGS microphone su Android 13/14/15/16, background/lockscreen e permessi; matrice eccezioni.

## Fonti primarie

- App Actions for Cars (agg. 2026-07-16): https://developer.android.com/develop/devices/assistant/cars
- Car microphone: https://developer.android.com/training/cars/apps/library/car-microphone
- Generic App Actions: https://developer.android.com/develop/devices/assistant/get-started
- Custom intents: https://developer.android.com/develop/devices/assistant/custom-intents
- Android background FGS restrictions: https://developer.android.com/develop/background-work/services/fgs/restrictions-bg-start
- FGS service types: https://developer.android.com/develop/background-work/services/fgs/service-types
- VoiceInteractionService: https://developer.android.com/reference/android/service/voice/VoiceInteractionService
- Android Auto voice button: https://support.google.com/androidauto/answer/6348334?hl=it
- Gemini in auto: https://support.google.com/gemini/answer/16735982?hl=it

**Avvertenza:** documentazione consultata ≠ prova runtime. Non classificare i PoC come eseguiti.
