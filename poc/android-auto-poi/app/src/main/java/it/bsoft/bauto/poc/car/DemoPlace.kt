package it.bsoft.bauto.poc.car

/**
 * Synthetic errands attached to demo coordinates in Turin.
 * No claims are made that these are real businesses or actual user tasks.
 */
internal data class DemoPlace(
    val id: String,
    val name: String,
    val errand: String,
    val latitude: Double,
    val longitude: Double,
    val distanceFromDemoCenterKm: Double
) {
    init {
        require(id.isNotBlank() && name.isNotBlank() && errand.isNotBlank())
        require(latitude in -90.0..90.0 && longitude in -180.0..180.0)
        require(distanceFromDemoCenterKm >= 0.0 && distanceFromDemoCenterKm.isFinite())
    }
}

internal object DemoPlacesRepository {
    const val CITY = "Torino (dati demo)"

    // Approximate coordinates only; do not use as real business addresses.
    private val places = listOf(
        DemoPlace("post", "Demo: ufficio postale", "Spedire un pacco", 45.0712, 7.6854, 0.15),
        DemoPlace("pharmacy", "Demo: farmacia", "Acquistare un articolo", 45.0670, 7.6902, 0.52),
        DemoPlace("pickup", "Demo: punto ritiro", "Ritirare un ordine", 45.0647, 7.6785, 0.91)
    )

    fun all(): List<DemoPlace> = places.toList()
}
