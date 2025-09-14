# Castled (Java-Chess-AI-Online-Play)
A web-based chess platform with a Java engine that plays at configurable strengths. Phase 1 focuses on a robust single-player experience vs. the AI. Phase 2 adds real-time multiplayer (play and spectate) using WebSockets.

### Engine: 
    -Move generation, legality, search, evaluation, FEN/PGN, perft instrumentation.

    -Deterministic given a seed; no logging side-effects by default (return telemetry objects).

### Server:
    REST lifecycle (create game, make move, analysis).

    WebSockets for live updates (Phase 2).

    Clocks, session authority, idempotent move application, PGN generation endpoints.

    Metrics, health/readiness, rate limiting, auth (Phase 2).

### UI:
    Board rendering and interaction.

    Move list, eval bar, PV display, clocks.

    Spectator views (Phase 2), simple room join/create.

### Common: 
    Contains Data Transfer Object, error model, validation annotations/specs, version constants.

### Documents:
    This will contain PDF's of my thought process and developement ideas.

# Test for readme.md

