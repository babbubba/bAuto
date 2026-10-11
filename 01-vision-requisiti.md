# BAuto --- Vision e requisiti iniziali

## Vision

BAuto non nasce come semplice registratore di note vocali. L'obiettivo è
un **assistente personale contestuale per l'automobile** capace di usare
voce, posizione, attività, progetti e contesto corrente per facilitare
azioni utili durante la guida.

## UX primaria

L'applicazione deve essere utilizzabile mentre si guida con: 1.
preferibilmente **attivazione vocale**; 2. in alternativa, **una sola
azione fisica** al massimo; 3. nessuna procedura che richieda
navigazione complessa dell'interfaccia.

L'esperienza ideale è alternativa o complementare a Google Assistant /
Hey Google. La fattibilità e il livello d'integrazione sono da
verificare.

## Casi d'uso iniziali

### Voice Inbox

L'utente detta liberamente un pensiero mentre guida.

Il sistema deve poter: - acquisire l'audio o la trascrizione; -
conservare il contenuto originale; - trascriverlo; - interpretarlo
tramite LLM; - classificare e taggare il contenuto; - associarlo, quando
possibile, a un progetto o argomento; - distinguere progressivamente
memo, task, idea, promemoria o altro tipo di elemento; - permetterne il
recupero successivo in modo organizzato.

### Nearby Tasks

L'utente può chiedere quali attività aperte siano sensate rispetto alla
posizione corrente.

Evoluzioni possibili: - distanza; - percorso o destinazione; - tempo
disponibile; - orari; - priorità; - vincoli associati all'attività; -
raggruppamento per progetto o contesto.

### AI Organizer / Knowledge Base

La classificazione non deve essere necessariamente richiesta durante la
cattura.

Principio: **l'utente parla, il sistema organizza dopo**.

L'LLM potrà inferire progressivamente: - progetto; - argomento; - tipo
di contenuto; - tag; - eventuale task; - priorità; - relazioni con altri
memo o attività.

## LLM

Il provider LLM deve essere configurabile.

Ipotesi iniziale: - configurazione principalmente dall'app sul
telefono; - astrazione del provider; - possibilità futura di supportare
provider cloud, OpenAI-compatible e/o modelli locali; - Android Auto usa
la configurazione già predisposta, senza diventare una console di
amministrazione.

## Componenti concettuali

-   App Android sul telefono.
-   Esperienza Android Auto, nei limiti consentiti dalla piattaforma.
-   Acquisizione vocale / speech-to-text.
-   Motore di classificazione LLM.
-   Archivio strutturato di memo, task, progetti e tag.
-   Motore contestuale per posizione/percorso e attività vicine.
-   Astrazione provider AI.

## Non deciso

Non sono ancora decisioni architetturali: - database; - framework UI; -
provider STT; - provider LLM predefinito; - modello dati definitivo; -
sincronizzazione cloud; - modalità precisa di attivazione vocale; -
categoria Android Auto con cui eventualmente distribuire l'app.
