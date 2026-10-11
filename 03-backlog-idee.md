# BAuto --- Backlog idee e questioni aperte

## Priorità immediata

-   [ ] Verificare ufficialmente capacità e policy Android Auto
    aggiornate.
-   [ ] Prototipare il percorso più rapido possibile per acquisire un
    memo vocale durante la guida.
-   [ ] Capire il livello reale di integrazione con Google Assistant /
    Hey Google.
-   [ ] Definire il modello minimo `Memo / Task / Project / Tag`.
-   [ ] Definire la pipeline
    `audio -> trascrizione -> classificazione -> persistenza`.
-   [ ] Definire l'astrazione dei provider LLM.

## Idee raccolte

-   Attività aperte nelle vicinanze.
-   Memo vocali durante la guida.
-   Trascrizione automatica.
-   Tagging tramite LLM.
-   Classificazione automatica.
-   Associazione a progetti.
-   Recupero intelligente delle informazioni.
-   Uso del contesto corrente per suggerire ciò che è utile adesso.
-   Configurazione LLM da telefono.
-   Possibile configurazione direttamente da Android Auto solo se
    sensata e consentita.

## Questioni aperte

-   BAuto deve mantenere anche l'audio originale o solo la trascrizione?
-   I task sono entità proprie o una classificazione di un memo?
-   I progetti sono creati esplicitamente o inferiti?
-   Serve sincronizzazione multi-device/cloud?
-   Quanto deve funzionare offline?
-   Quali dati di posizione vengono persistiti?
-   L'organizzazione AI è automatica, confermata dall'utente o entrambe?
-   Come correggere classificazioni errate senza aumentare l'attrito?
-   Quale deve essere il comportamento quando il modello AI non è
    raggiungibile?

## Metodo di raccolta

I prossimi input vocali possono essere grezzi, incompleti o
contraddittori. Prima di consolidarli: 1. registrarli come
requisito/idea; 2. distinguere fatto, desiderio, ipotesi e decisione; 3.
evidenziare eventuali conflitti; 4. consolidare solo quando emerge una
scelta.
