# ADR-003 — Use host-rendered PlaceListMapTemplate for first Nearby Tasks PoC

Status: Proposed
Date: 2026-10-05

## Decision
POC-AA-001 will use `PlaceListMapTemplate`, not a custom rendered map.

## Rationale
It is explicitly designed for POI, minimizes rendering complexity, maximizes alignment with Google's intended category experience, and is sufficient to validate Nearby Tasks semantics.

## Consequence
`MapWithContentTemplate` remains an optimization/UX option after eligibility is proven.
