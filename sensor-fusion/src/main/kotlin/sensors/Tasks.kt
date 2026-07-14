package sensors

/**
 * SensorFusion practice.
 *
 * Two strands: sensor math (separating gravity from motion, and turning a
 * noisy signal into discrete shake events without double-counting), and BLE
 * (a GATT connection state machine, and the rule that GATT operations must
 * be serialized rather than run concurrently).
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :sensor-fusion:test
 */

/** A raw or filtered 3-axis accelerometer reading. */
data class Vector3(val x: Float, val y: Float, val z: Float)

/** The rates SensorManager lets you request delivery at. */
enum class SamplingRate { NORMAL, UI, GAME, FASTEST }

/** Roughly how much battery a sampling rate costs to keep running. */
enum class PowerCost { LOW, MEDIUM, HIGH, MAXIMUM }

// TODO(t1): T1PowerCostTest
// Map a sensor sampling rate to its approximate power cost: NORMAL is fine
// for background/ambient use, FASTEST drains the battery fastest.
fun powerCost(rate: SamplingRate): PowerCost {
    TODO("t1: NORMAL->LOW, UI->MEDIUM, GAME->HIGH, FASTEST->MAXIMUM")
}

// TODO(t2): T2LowPassFilterTest
// Apply a low-pass filter to isolate the gravity component from a raw
// accelerometer reading: each axis of `gravity` moves toward the matching
// axis of `raw` by (1 - alpha), staying mostly at its old value when alpha
// is close to 1 and snapping straight to `raw` when alpha is 0.
fun lowPassFilter(gravity: Vector3, raw: Vector3, alpha: Float): Vector3 {
    TODO("t2: gravity * alpha + raw * (1 - alpha), per axis")
}

// TODO(t3): T3LinearAccelerationTest
// Strip gravity out of a raw accelerometer reading to get the linear,
// user-caused acceleration: raw minus the current gravity estimate, per
// axis.
fun linearAcceleration(raw: Vector3, gravity: Vector3): Vector3 {
    TODO("t3: raw - gravity, per axis")
}

/** One accelerometer sample: linear-acceleration magnitude at a point in time. */
data class SensorSample(val timestampMs: Long, val magnitude: Float)

// TODO(t4): T4DetectShakesTest
// Walk `samples` in order and count a "shake" every time magnitude crosses
// above `threshold`, but treat any further over-threshold sample within
// `debounceMs` of the last COUNTED shake as the same shake, not a new one.
// Return the timestamps of the shakes actually counted, in order.
fun detectShakes(samples: List<SensorSample>, threshold: Float, debounceMs: Long): List<Long> {
    TODO("t4: count a new shake only when magnitude > threshold AND debounceMs has elapsed since the last counted shake")
}

/** The states a GATT client connection moves through. */
enum class GattState { DISCONNECTED, CONNECTING, CONNECTED, DISCOVERING_SERVICES, READY }

/** Events that drive a GATT connection from one state to the next. */
enum class GattEvent { CONNECT, CONNECTION_ESTABLISHED, START_SERVICE_DISCOVERY, SERVICES_DISCOVERED, DISCONNECT }

// TODO(t5): T5NextGattStateTest
// Apply one incoming GattEvent to the current GattState and return the
// resulting state, following the real BLE connection sequence DISCONNECTED
// -> CONNECTING -> CONNECTED -> DISCOVERING_SERVICES -> READY. DISCONNECT is
// legal from every state except DISCONNECTED itself and always lands back on
// DISCONNECTED. Any other combination is not a real transition and must
// throw IllegalStateException.
fun nextGattState(current: GattState, event: GattEvent): GattState {
    TODO("t5: a when over (current, event) matching the real BLE sequence, else throw IllegalStateException")
}

/** A single request against a GATT connection, e.g. a characteristic read or write. */
data class GattOperation(val id: String)

/** What's currently running against the connection, and what's waiting its turn. */
data class GattQueueState(val inFlight: GattOperation?, val pending: List<GattOperation>)

// TODO(t6): T6EnqueueGattOperationTest
// Submit `op` against `state`. If nothing is in flight, `op` becomes the
// new in-flight operation immediately. If something is already in flight,
// append `op` to the end of the pending list instead, so a second request
// queues rather than clobbering the operation already running.
fun enqueueGattOperation(state: GattQueueState, op: GattOperation): GattQueueState {
    TODO("t6: start immediately if idle, otherwise append to pending")
}

// TODO(t7): T7CompleteGattOperationTest
// The in-flight operation has finished. Clear it, then promote the next
// pending operation, if any, to in-flight, removing it from the pending
// list. Calling this when nothing is in flight returns `state` unchanged.
fun completeGattOperation(state: GattQueueState): GattQueueState {
    TODO("t7: clear inFlight, then promote the head of pending if there is one")
}
