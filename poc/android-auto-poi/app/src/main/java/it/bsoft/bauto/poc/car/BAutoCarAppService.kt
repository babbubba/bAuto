package it.bsoft.bauto.poc.car

import android.content.Intent
import androidx.car.app.CarAppService
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.car.app.SessionInfo
import androidx.car.app.validation.HostValidator
import it.bsoft.bauto.poc.BuildConfig

/**
 * Android Auto projected entry point.
 *
 * Accepting arbitrary hosts is ONLY permitted for debug/DHU experiments.
 * Release builds use the official signed-host allowlist.
 */
class BAutoCarAppService : CarAppService() {
    override fun createHostValidator(): HostValidator =
        if (BuildConfig.DEBUG) {
            HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        } else {
            HostValidator.Builder(this)
                .addAllowedHosts(androidx.car.app.R.array.hosts_allowlist_sample)
                .build()
        }

    override fun onCreateSession(sessionInfo: SessionInfo): Session = BAutoSession()
}

private class BAutoSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen =
        NearbyTasksScreen(carContext)
}
