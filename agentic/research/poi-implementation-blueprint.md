# TASK-001 — POI implementation blueprint

Verified: 2026-10-05

## Minimal Android Auto POI plumbing

The projected Android Auto experience is a service-based Car App Library surface.

Required shape:

1. Mobile application owns runtime/data.
2. Android manifest declares Android Auto metadata pointing to `res/xml/automotive_app_desc.xml`.
3. `automotive_app_desc.xml` declares `<uses name="template" />`.
4. A service extends `CarAppService`.
5. Its intent filter contains:
   - `androidx.car.app.CarAppService`
   - `androidx.car.app.category.POI`
6. For POI map templates, declare `androidx.car.app.MAP_TEMPLATES`.
7. `CarAppService.onCreateSession()` returns a `Session`.
8. `Session.onCreateScreen()` returns the first `Screen`.
9. `Screen.onGetTemplate()` returns host-rendered templates.

## Candidate BAuto car flow

### Home / Nearby Tasks
Use `PlaceListMapTemplate` first.

Each item represents a **real place** at which a pending user activity can be performed:
- buy a needed item;
- collect an order;
- visit a business/service;
- perform a location-bound errand.

Do not make arbitrary non-location tasks appear as fake POIs.

### Refresh
`PlaceListMapTemplate.Builder.setOnContentRefreshListener` can request a new ranking. After data refresh call `Screen.invalidate()`. Returning the same template type is treated as a refresh rather than another template in the task quota.

This is suitable for:
- position changed;
- opening status changed;
- ranking changed;
- user asks for another set of nearby opportunities.

### Details
Keep details shallow:
- place/task title;
- distance;
- why it is relevant;
- one primary action;
- optional handoff to navigation where supported.

### Voice
Voice capture is a separate action using `CarAudioRecord`; it should not be required to make the POI category semantically valid.

## Map choice

### `PlaceListMapTemplate`
Preferred for POC-AA-001:
- map rendered by host;
- explicitly intended for POI;
- minimizes custom rendering and implementation surface.

### `MapWithContentTemplate`
Potential later option:
- can combine content with a map rendered by the app;
- more control, but requires implementing the map surface.
Not needed to validate BAuto eligibility.

## Testing

Use DHU for:
- template rendering;
- refresh behavior;
- task depth;
- light/dark/contrast;
- latency;
- driving restrictions;
- microphone behavior.

Use a real Android Auto vehicle after DHU validation through trusted/internal distribution.

## Important distinction

The official `android/car-samples` showcase is useful as API/template reference, but a showcase exercising many categories is **not evidence that a production app may combine unrelated categories/features**. Production eligibility remains governed by Car App Quality.
