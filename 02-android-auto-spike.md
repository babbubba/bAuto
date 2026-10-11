# BAuto --- Spike Android Auto

## Obiettivo

Determinare quale esperienza BAuto possa offrire realmente su Android
Auto e quale debba invece risiedere nell'app Android sul telefono.

## Vincolo noto

Non assumere che una normale applicazione Android possa esporre
liberamente qualunque UI o comportamento su Android Auto. L'esperienza
auto deve rispettare categorie, API, template e policy supportati dalla
piattaforma.

## Requisito critico da investigare

L'utente desidera usare BAuto: - idealmente tramite attivazione
vocale; - possibilmente in alternativa a Hey Google; - oppure in
coordinazione con Google Assistant; - senza interagire manualmente
durante la guida; - fallback accettabile: una singola azione fisica.

## Spike prioritari

### AA-001 --- Attivazione vocale

Verificare: - quali voice actions sono disponibili; - se e come Google
Assistant può invocare una funzione specifica di BAuto; - possibilità e
limiti di un'esperienza conversazionale; - possibilità di avviare
rapidamente una cattura vocale; - restrizioni su hotword o ascolto
continuo.

### AA-002 --- Categoria applicativa

Verificare se i casi d'uso BAuto possono rientrare legittimamente in
categorie Android Auto supportate.

In particolare investigare, senza assumerne l'applicabilità: - POI; -
communication; - altre categorie eventualmente disponibili nella
versione corrente della Car App Library.

### AA-003 --- Nearby Tasks

Verificare se una rappresentazione di attività contestuali legate a
luoghi possa essere compatibile con le API/template POI e con le policy
di distribuzione.

### AA-004 --- UI minima

Definire il massimo flusso consentito con: - zero touch; - one touch; -
input vocale; - feedback vocale; - eventuali template Android Auto.

### AA-005 --- Architettura telefono/auto

Validare una separazione in cui: - il telefono contiene configurazione,
storage e servizi principali; - Android Auto espone soltanto
l'interazione ammessa e necessaria.

## Regola progettuale

Nessuna funzionalità core di BAuto deve dipendere da una capacità
Android Auto non ancora verificata.
