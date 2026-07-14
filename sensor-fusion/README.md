# SensorFusion

Practice module for the **Hardware: Sensors & BLE** topic on Android
Interview Prep.

Two testable strands. Sensors: a low-pass filter that separates gravity
from motion, the subtraction that turns that into linear acceleration, a
threshold-plus-debounce shake detector so one shake isn't counted many
times, and mapping a sampling rate to its power cost. BLE: a GATT
connection state machine that rejects illegal transitions, and the rule
that GATT operations must be serialized, one in flight at a time, so a
second request queues instead of clobbering the one already running. Each
task is a small function that's currently broken or unwritten, with a
matching test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :sensor-fusion:test                       # run everything
./gradlew :sensor-fusion:test --tests "*T1PowerCostTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/sensors/Tasks.kt`.

1. **`powerCost`** (`T1PowerCostTest`) - map a sensor sampling rate to its approximate battery cost.
2. **`lowPassFilter`** (`T2LowPassFilterTest`) - isolate the gravity component from a raw accelerometer reading.
3. **`linearAcceleration`** (`T3LinearAccelerationTest`) - strip gravity out to get the motion the user actually caused.
4. **`detectShakes`** (`T4DetectShakesTest`) - threshold plus debounce, so a single shake doesn't get counted many times.
5. **`nextGattState`** (`T5NextGattStateTest`) - the GATT connection state machine, disconnected through ready, rejecting illegal transitions.
6. **`enqueueGattOperation`** (`T6EnqueueGattOperationTest`) - a second request queues instead of clobbering the operation already in flight.
7. **`completeGattOperation`** (`T7CompleteGattOperationTest`) - finishing one operation promotes the next queued one, if any.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
