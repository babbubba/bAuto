# ADR-002 — POI as candidate Android Auto category for Nearby Tasks

Status: Proposed
Date: 2026-10-05

## Context
POI is a supported Android Auto category. Google requires meaningful functionality relevant to driving and prohibits features outside intended car app types.

## Proposed decision
Model the initial Android Auto discovery surface around Nearby Tasks as genuine POI functionality. Do not use POI merely as a wrapper for generic memo/project functionality.

## Validation required
- Minimal POI PoC.
- Car App Quality checklist.
- Closed-track Play review as an early eligibility experiment.
- Community/rejection evidence review.

## Rejection condition
If the core Nearby Tasks semantics are rejected as non-POI, this ADR is rejected and the Android Auto product scope is redesigned.
