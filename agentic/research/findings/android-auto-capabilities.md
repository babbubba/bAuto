# TASK-001 --- Android Auto capabilities & eligibility research

**Verified:** 2026-10-05\
**Status:** research baseline --- primary-source findings established;
PoC/review-risk validation still required.

## Executive finding

BAuto can technically implement a substantial part of the desired in-car
experience with the Android for Cars App Library, including templated
UI, POI/map experiences and direct access to the car microphone.

The principal feasibility risk is **eligibility**, not basic API
capability: an Android Auto experience must genuinely fit a supported
car-app category and satisfy the category's quality requirements.
Declaring a category in the manifest does not make an otherwise
unrelated experience eligible.

For the current BAuto concept, the strongest candidate category is
**Point of Interest (POI)** for the "Nearby Tasks" experience. The
generic memo / personal knowledge assistant is not, by itself, an
established Android Auto category.

## Current platform baseline

-   Stable Android for Cars App Library: `1.7.0` (2026-09-09).
-   RC: `1.8.0-rc01`.
-   Alpha: `1.9.0-alpha02`.
-   Android Auto is a projected experience: the app executes on the
    connected Android phone and exposes a driver-optimized experience to
    the head unit.
-   Android Auto phone compatibility starts at Android 9 / API 28.

## Supported categories relevant to the investigation

Current Android Auto categories include:

-   Media
-   Communication / messaging
-   Communication / calling
-   Navigation
-   Point of Interest (POI)
-   Internet of Things
-   Weather
-   Games

Some newer/templated communication and media experiences are marked as
labs/EAP or have track restrictions. Category availability must
therefore be checked again immediately before release.

### BAuto fit assessment

  --------------------------------------------------------------------------
  BAuto capability        Candidate category /       Preliminary status
                          mechanism                  
  ----------------------- -------------------------- -----------------------
  Nearby Tasks tied to    POI                        Strong candidate;
  places                                             semantics/review must
                                                     be validated

  Show nearby actionable  POI +                      Supported technically
  locations               `PlaceListMapTemplate` /   
                          `MapWithContentTemplate`   

  Search/list contextual  POI + general templates    Supported technically
  places                                             

  Record spoken request   `CarAudioRecord`           Supported technically,
  from car microphone                                Car API 5+

  In-app AI assistant     Car microphone + app       Explicitly contemplated
  after explicit          processing                 by Google UX docs
  microphone activation                              

  Generic voice memo      No dedicated category      Eligibility risk
  capture                 identified                 

  Generic project/task    No dedicated category      High eligibility risk
  knowledge manager       identified                 on car UI

  Arbitrary "Hey Google,  App Actions for Cars       Not supported by
  take a BAuto note"                                 currently documented
                                                     car BIIs

  "Hey Google" POI        App Actions for Cars       Very limited: parking
  actions                                            and charging BIIs only

  Configuration/API       Phone app                  Preferred; avoid car UI
  keys/model selection                               complexity
  --------------------------------------------------------------------------

## Android for Cars App Library

The architecture is host-driven:

1.  The phone app declares Android Auto support.
2.  A `CarAppService` is exposed.
3.  The Android Auto host connects to the service.
4.  The app returns supported `Template` models rather than rendering an
    arbitrary phone UI on the head unit.

For a templated Android Auto app the manifest includes the Android Auto
metadata and `automotive_app_desc.xml` declares the `template`
capability.

The app also declares its actual car category on the `CarAppService`,
for example `androidx.car.app.category.POI`.

### General templates

Relevant available templates include:

-   `ListTemplate`
-   `GridTemplate`
-   `SectionedItemTemplate`
-   `PaneTemplate`
-   `MessageTemplate`
-   `LongMessageTemplate`
-   `SearchTemplate`
-   `SignInTemplate`
-   `TabTemplate`

Special-purpose templates include:

-   `PlaceListMapTemplate` --- POI
-   `MapWithContentTemplate` --- Navigation / POI / Weather
-   `NavigationTemplate` --- Navigation
-   `MediaPlaybackTemplate` --- Media

This means BAuto does not need a custom Android UI on the head unit for
its basic car experience; the host renders constrained templates.

## POI capability

POI apps declare:

`androidx.car.app.category.POI`

The category can use `PlaceListMapTemplate` and
`MapWithContentTemplate`.

`PlaceListMapTemplate` is particularly relevant to Nearby Tasks because
the host renders a map plus a list of places.

### Critical quality criterion

`PF-1`: a POI app must provide **meaningful functionality relevant to
driving**.

`PC-1`: an app must not include features outside the app types intended
for cars.

Interpretation for BAuto:

-   "I am driving; show useful errands/tasks associated with locations
    near my current position or route" is plausibly POI-like.
-   "Manage arbitrary software-project notes on the car display" is not
    inherently POI functionality.
-   The POI classification must not be used merely as a technical route
    to expose a generic assistant.

This requires a review-oriented product definition before publication.

## Car microphone and in-app assistant

This is a strong positive finding.

Google explicitly documents voice input as suitable for features such as
an **in-app digital assistant**.

The API is `androidx.car.app.media.CarAudioRecord`.

Properties documented by the API:

-   introduced in Car App Library `1.3.0`;
-   requires Car API level 5;
-   requires `android.permission.RECORD_AUDIO`;
-   raw format: `audio/l16`;
-   sample rate: 16 kHz;
-   app reads microphone bytes directly;
-   app can process the stream itself;
-   recording must acquire appropriate audio focus;
-   when the user dismisses microphone input, `read()` returns `-1`.

This is sufficient in principle for a pipeline such as:

`Car microphone -> CarAudioRecord -> VAD/end-of-speech -> STT -> LLM/tool router -> BAuto action -> concise car feedback`

### Interaction constraint

Google's documented UX expects the user to initiate voice input, for
example by tapping a microphone action.

Therefore:

-   **one-tap voice assistant:** strong technical feasibility;
-   **BAuto-owned always-listening hotword:** not established by this
    research;
-   **arbitrary invocation through Hey Google:** not provided by the
    generic microphone API.

Permissions should preferably be obtained before driving. On Android
Auto, runtime permission UI is presented on the phone; the car UI must
instruct the user to use the phone only when safe.

## Gemini / Google Assistant / App Actions

Google documents App Actions for Cars.

This can let Gemini or Google Assistant invoke functionality in a car
app by voice.

However, the current documented car implementation is narrowly
constrained:

-   the app must use the Car App Library;
-   it must declare the POI category;
-   fulfillment uses deep links;
-   only these car BIIs are documented:
    -   `GET_PARKING_FACILITY`
    -   `GET_CHARGING_STATION`

Therefore the desired phrase:

> "Hey Google, take a note with BAuto"

cannot currently be assumed implementable through App Actions for Cars.

This remains a separate research/spike topic rather than a blocker for
the one-tap `CarAudioRecord` experience.

## Task-flow restrictions

Car experiences are intentionally constrained.

The host normally limits a task to at most five templates/screens under
the documented task-limit rules. Refreshes and back/reset operations
have specific exceptions.

Implication: BAuto car workflows should be deliberately shallow.

Candidate target:

1.  open BAuto;
2.  one microphone action;
3.  speak;
4.  process;
5.  audio/toast confirmation.

Complex editing, provider setup, taxonomy management and project
administration belong on the phone.

## Location and car data

The Car App Library exposes Car Hardware APIs from Car API level 3.

`CarSensors` can expose, subject to host/OEM support:

-   accelerometer;
-   gyroscope;
-   compass;
-   location.

Car location requires fine-location permission.

Availability can vary by OEM, so BAuto must not make its core Nearby
Tasks feature depend exclusively on vehicle-provided location. Standard
phone Android location remains an architectural candidate and must be
compared in TASK-009.

## Testing path

### Development

Use Android Auto **Desktop Head Unit (DHU)**.

DHU:

-   runs on Windows, macOS and Linux;
-   connects to an Android phone;
-   supports microphone input;
-   can play prerecorded WAV commands;
-   can simulate car sensor data.

This gives us a realistic local PoC path.

### Real vehicle

Google states that real-vehicle apps normally need a trusted install
source.

For development/testing:

-   Internal App Sharing;
-   Internal Test Track.

The Android Auto "unknown sources" developer option does **not** apply
generally to apps built using the Android for Cars App Library.

### Important release precaution

Google's quality documentation cautions against prototyping Android Auto
support in the production APK because car-app approval can hold
subsequent distribution while review is pending.

Use a dedicated test application/package or controlled release strategy
for early BAuto experiments.

## Google Play eligibility path

Provisional path:

1.  Define exactly which BAuto features are exposed in the car.
2.  Select a supported category based on actual functionality, not
    desired APIs.
3.  For Nearby Tasks, prototype a genuine POI experience.
4.  Build the projected Android Auto module with `androidx.car.app`.
5.  Declare Android Auto metadata, `CarAppService`, category and minimum
    Car API level.
6.  Implement only category-compatible driving functionality.
7.  Implement microphone permission onboarding on the phone.
8.  Validate every applicable Car App Quality criterion.
9.  Test with DHU, including task limits, driving state, errors and
    microphone.
10. Test through Internal App Sharing/Internal Test Track and a real
    vehicle.
11. Prepare Play listing/declared car support consistently with actual
    functionality.
12. Submit for car-app quality review.
13. Treat review feedback as a product/category signal, not merely a
    manifest problem.

## Review risk found in community/issue reports

Historical developer reports show applications declaring POI or IoT
being rejected with "Category not permitted" even when developers
believed their manifests matched documented categories.

These reports are not normative and some concern older CAL versions, but
they are useful evidence that Play review evaluates the real experience
and can be inconsistent or require appeal/clarification.

For BAuto, category compliance should therefore be treated as a release
risk requiring an early minimal submission experiment, not left until
the end of product development.

## Initial capability matrix

  --------------------------------------------------------------------------
  Requirement                Result                  Confidence
  -------------------------- ----------------------- -----------------------
  Android Auto templated UI  Supported               High

  Lists/grids/search/panes   Supported               High

  POI list + map             Supported for POI apps  High

  Access car microphone      Supported, Car API 5+   High

  Send raw speech to own STT Technically supported   High
                             by audio access         

  In-app digital assistant   Explicitly contemplated High

  One-tap start voice        Supported design        High
  interaction                pattern                 

  Zero-touch custom BAuto    Not established         Low / requires research
  hotword                                            

  Generic Hey Google -\>     Current App Actions for High
  BAuto note                 Cars do not expose a    
                             matching BII            

  Hey Google                 Supported App Actions   High
  parking/charging through   path                    
  POI app                                            

  Generic memo manager as    No supported category   High risk
  Android Auto category      identified              

  Nearby Tasks as POI        Plausible, review       Medium
                             semantics unresolved    

  Complex configuration on   Technically/UX          High
  car UI                     inappropriate           

  Configure on phone,        Appropriate             High
  consume in car             architecture candidate  

  Test locally before Play   Supported via DHU       High
  review                                             

  Test on real vehicle       Supported via internal  High
  before production review   distribution            
  --------------------------------------------------------------------------

## Required PoCs / follow-up

### POC-AA-001 --- Minimal POI

Build the smallest valid POI app: - one nearby task/place list; -
`PlaceListMapTemplate`; - one details action; - no AI.

Purpose: validate host behavior and category plumbing.

### POC-AA-002 --- CarAudioRecord

-   microphone button;
-   acquire audio focus;
-   record PCM;
-   detect stop/end-of-speech;
-   persist/send stream;
-   display concise acknowledgement.

Purpose: prove the core BAuto voice capture path.

### POC-AA-003 --- STT round trip

`CarAudioRecord -> STT -> structured result -> confirmation`.

Measure: - first-audio latency; - end-of-speech latency; - transcription
latency; - total response latency; - network failure behavior.

### POC-AA-004 --- Early Play category validation

Once the minimal POI experience is honest and usable, submit through an
appropriate non-production test/review route as early as practical.

Purpose: discover category/review blockers before building the complete
product.

## Sources

Primary sources should be rechecked immediately before
implementation/release:

-   Android for Cars overview
-   Android for Cars App Library
-   Car App Quality guidelines
-   Build a POI app
-   Record from the car microphone / `CarAudioRecord`
-   App Actions for Cars
-   Template restrictions
-   Request permissions
-   Test Android apps for cars / Desktop Head Unit
-   Distribute to cars
-   AndroidX Car App release notes
-   Official Android `car-samples` and `car-codelabs` repositories

Community/issue reports are retained only as supporting evidence for
review risk, never as authoritative API/policy definitions.
