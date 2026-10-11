# ADR-001 — Phone-centric architecture with projected Android Auto surface

Status: Proposed
Date: 2026-10-05

## Context
Android Auto projected apps run as part of the mobile application and expose a host-rendered car experience through Android for Cars APIs. BAuto also needs configuration, project management, provider secrets, rich editing and review workflows that are unsuitable for the driving surface.

## Proposed decision
Use the phone application as the primary runtime/data/configuration owner. Android Auto is a constrained projected surface for driver-safe workflows.

## Consequences
- API keys/provider configuration stay phone-side.
- Rich memo/project editing stays phone-side.
- Car flows remain shallow and category-compliant.
- Shared domain/application services can serve phone and car surfaces.

## Status rationale
Strongly supported by platform architecture, but Accepted is deferred until TASK-001/TASK-002 complete.
