# BAuto

BAuto è un assistente contestuale per l'automobile, con un'interazione prevalentemente vocale e a minima distrazione.

## Principio

**Capture first, organize later.** La registrazione immediata durante la guida e l'organizzazione successiva con AI sono obiettivi di prodotto, non funzionalità già approvate per Android Auto.

## Stato (2026-10-11)

Milestone **M0 — Research & Feasibility**. TASK-001 e TASK-003 hanno completato la ricerca documentale; TASK-002 e i PoC runtime restano aperti.

**La categoria Android Auto POI non autorizza automaticamente memo vocali generici o project management.** La distribuzione Play richiede verifica del prodotto reale e dei criteri PC-1/PF-1.

## Repository

- [Stato progetto](PROJECT-STATUS-2026-10-11.md)
- [Master Plan](agentic/planning/MASTER_PLAN.md)
- [Backlog](agentic/planning/BACKLOG.md)
- [Task index](agentic/tasks/INDEX.md)
- [Policy & distribution](agentic/research/findings/policy-distribution.md)
- [ADR](agentic/architecture/adr/)
- [Research findings](agentic/research/findings/)
- [POC-AA-001 — Android Auto POI](poc/android-auto-poi/README.md)

## PoC

Un prototipo Kotlin/Gradle minimale è disponibile in `poc/android-auto-poi`. Usa luoghi e commissioni **dimostrativi/fittizi**, nessuna acquisizione GPS e nessun microfono; non è pronto per Google Play.

## Versionamento

GitHub è la fonte autorevole. Usare branch/PR per modifiche, registrare fonti e test eseguiti; non promuovere ADR a Accepted senza gate espliciti.
