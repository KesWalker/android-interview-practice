package activitylife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T6ResourceBindingTest {

    @Test
    fun `view binding is scoped to the whole activity lifetime`() {
        assertEquals(
            ResourceBinding(Callback.ON_CREATE, Callback.ON_DESTROY),
            lifecycleBinding(WorkKind.VIEW_BINDING)
        )
    }

    @Test
    fun `camera preview and location updates are scoped to visibility`() {
        val expected = ResourceBinding(Callback.ON_START, Callback.ON_STOP)

        assertEquals(expected, lifecycleBinding(WorkKind.CAMERA_PREVIEW))
        assertEquals(expected, lifecycleBinding(WorkKind.LOCATION_UPDATES))
    }

    @Test
    fun `a sensor listener is scoped to being resumed, not merely visible`() {
        assertEquals(
            ResourceBinding(Callback.ON_RESUME, Callback.ON_PAUSE),
            lifecycleBinding(WorkKind.SENSOR_LISTENER)
        )
    }
}
