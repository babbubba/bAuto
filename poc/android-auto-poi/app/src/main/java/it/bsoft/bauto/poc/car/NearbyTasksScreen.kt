package it.bsoft.bauto.poc.car

import android.text.SpannableString
import android.text.Spanned
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.CarLocation
import androidx.car.app.model.Distance
import androidx.car.app.model.DistanceSpan
import androidx.car.app.model.ItemList
import androidx.car.app.model.Metadata
import androidx.car.app.model.Place
import androidx.car.app.model.PlaceListMapTemplate
import androidx.car.app.model.PlaceMarker
import androidx.car.app.model.Row
import androidx.car.app.model.Template

/** Host-rendered POI list. Every item has location metadata and a DistanceSpan. */
internal class NearbyTasksScreen(carContext: CarContext) : Screen(carContext) {
    private var reverseOrder = false

    override fun onGetTemplate(): Template {
        val all = DemoPlacesRepository.all()
        val items = if (reverseOrder) all.asReversed() else all

        val list = ItemList.Builder()
            .setNoItemsMessage("Nessun punto demo")
        for (item in items) {
            val distance = SpannableString(" ")
            distance.setSpan(
                DistanceSpan.create(
                    Distance.create(item.distanceFromDemoCenterKm, Distance.UNIT_KILOMETERS)
                ),
                0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            val location = Place.Builder(CarLocation.create(item.latitude, item.longitude))
                .setMarker(PlaceMarker.Builder().build())
                .build()
            list.addItem(
                Row.Builder()
                    .setTitle(item.name)
                    .addText(distance)
                    .setMetadata(Metadata.Builder().setPlace(location).build())
                    .setOnClickListener {
                        screenManager.push(PlaceDetailsScreen(carContext, item))
                    }
                    .build()
            )
        }

        return PlaceListMapTemplate.Builder()
            .setTitle(DemoPlacesRepository.CITY)
            .setHeaderAction(Action.APP_ICON)
            .setCurrentLocationEnabled(false)
            .setItemList(list.build())
            // Deliberately reverses demo ordering to prove host-template refresh semantics.
            .setOnContentRefreshListener {
                reverseOrder = !reverseOrder
                invalidate()
            }
            .build()
    }
}
