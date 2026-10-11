# TASK-002 — Voice capability matrix

Verificata: 2026-10-11

| Requisito | Esito | Confidenza | Azione |
|---|---|---|---|
| Registrare da microfono auto con azione esplicita | Tecnico: sì | Alta | PoC-AA-002 / POC-VOICE-001 |
| Attivare memo generico con Hey Google in Android Auto | Non supportato dai BII car correnti | Alta | Non promettere |
| Usare Assistant per parcheggio/ricarica | Sì per POI conformi | Alta | Solo se caso d'uso reale |
| Lanciare BAuto telefono con voce | Documentato genericamente | Media | Verificare con AA connesso |
| Riassegnare pulsante volante a BAuto | Non documentato | Media | Non progettare dipendenza |
| Hotword BAuto proprietaria | Incerta, assistente di sistema separato | Bassa | PoC dedicato, non MVP |
| FGS microfono avviato in background | Limitato dal sistema | Alta | Test versioni Android |
| Trascrizione e classificazione lato telefono | Architettura candidata | Alta | TASK-005/006 |

**Decisione:** priorità a one-touch, mantenendo zero-touch come obiettivo sperimentale non garantito.
