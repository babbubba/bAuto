package it.bsoft.bauto.poc.car

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DemoPlacesRepositoryTest {
    @Test
    fun `sample locations are finite and unique`() {
        val all = DemoPlacesRepository.all()
        assertTrue(all.size >= 3)
        assertEquals(all.size, all.map { it.id }.toSet().size)
        assertTrue(all.all {
            it.latitude in -90.0..90.0 &&
                it.longitude in -180.0..180.0 &&
                it.distanceFromDemoCenterKm.isFinite()
        })
    }

    @Test
    fun `repository returns a fresh list`() {
        val first = DemoPlacesRepository.all()
        val second = DemoPlacesRepository.all()
        assertEquals(first, second)
        assertTrue(first !== second)
    }
}
