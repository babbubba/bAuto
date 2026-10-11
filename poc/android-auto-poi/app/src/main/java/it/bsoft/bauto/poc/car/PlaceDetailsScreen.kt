package it.bsoft.bauto.poc.car

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

/**
 * Two-screen navigation flow (list -> details -> back).
 * Real navigation is intentionally excluded for synthetic POIs.
 */
internal class PlaceDetailsScreen(
    carContext: CarContext,
    private val place: DemoPlace
) : Screen(carContext) {
    override fun onGetTemplate(): Template =
        PaneTemplate.Builder(
            Pane.Builder()
                .addRow(Row.Builder().setTitle("Commissione (demo)").addText(place.errand).build())
                .addRow(Row.Builder().setTitle("Informazioni").addText("Coordinate e attività fittizie. Non usare per navigare.").build())
                .build()
        )
            .setTitle(place.name)
            .setHeaderAction(Action.BACK)
            .build()
}
