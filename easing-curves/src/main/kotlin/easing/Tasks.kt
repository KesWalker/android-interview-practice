package easing

/**
 * Easing practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :easing-curves:test
 */

data class Point(val x: Float, val y: Float)

data class Keyframe(val timeFraction: Float, val value: Float)

/** A running animation's current sample: where it is, and how fast it's moving. */
data class AnimationState(val value: Float, val velocity: Float)

// TODO(t1): T1LerpTest
// Linearly interpolate between `start` and `end` at `fraction`. fraction 0
// returns start, fraction 1 returns end, fraction 0.5 returns the midpoint.
// Don't clamp, a fraction outside [0, 1] should extrapolate past the ends.
fun lerp(start: Float, end: Float, fraction: Float): Float {
    TODO("t1: start + (end - start) * fraction, no clamping")
}

// TODO(t2): T2CubicBezierPointTest
// Evaluate the point at parameter `t` on the cubic Bezier curve with
// control points p0, p1, p2, p3, using the standard Bernstein form:
// B(t) = (1-t)^3 p0 + 3(1-t)^2 t p1 + 3(1-t) t^2 p2 + t^3 p3
// applied to the x and y components independently.
fun cubicBezierPoint(p0: Point, p1: Point, p2: Point, p3: Point, t: Float): Point {
    TODO("t2: Bernstein-form cubic Bezier, evaluated per axis")
}

// TODO(t3): T3SampleTweenTest
// `keyframes` is sorted by timeFraction and always has at least two entries
// spanning [0, 1]. Sample the tween at `t`: find the two keyframes
// surrounding t, linearly interpolate between their values using the
// fraction of the way t is between their two times. When t is at or before
// the first keyframe's time, clamp to the first keyframe's value; when t is
// at or after the last keyframe's time, clamp to the last keyframe's value.
fun sampleTween(keyframes: List<Keyframe>, t: Float): Float {
    TODO("t3: find the surrounding keyframe pair and lerp between them, clamped at the ends")
}

// TODO(t4): T4CubicBezierEaseTest
// A real easing curve like Compose's CubicBezierEasing is defined by control
// points (x1, y1) and (x2, y2), with the curve's own endpoints fixed at
// (0, 0) and (1, 1). Given a progress `fraction` along the X axis (time),
// find the curve parameter t such that the X component of the Bezier curve
// (with control points (0,0), (x1,y1), (x2,y2), (1,1)) equals `fraction`,
// by binary search over t in [0, 1] until the search interval is under
// 0.0001 wide, then return the Y component of the curve at that t. X is
// monotonic in t for a valid easing curve, so binary search on "is
// bezierX(mid) less than fraction" is safe.
fun cubicBezierEase(x1: Float, y1: Float, x2: Float, y2: Float, fraction: Float): Float {
    TODO("t4: binary search for t where bezierX(t) == fraction, then return bezierY(t)")
}

// TODO(t5): T5SpringStepTest
// Advance a damped spring toward `target` by one fixed timestep `dt`, using
// semi-implicit (symplectic) Euler integration with mass = 1:
//   acceleration = stiffness * (target - state.value) - damping * state.velocity
//   newVelocity  = state.velocity + acceleration * dt
//   newValue     = state.value + newVelocity * dt
// Return the resulting AnimationState.
fun springStep(state: AnimationState, target: Float, stiffness: Float, damping: Float, dt: Float): AnimationState {
    TODO("t5: one semi-implicit Euler step of a damped spring toward target")
}

// TODO(t6): T6RetargetSpringTest
// Simulate a spring in two phases without ever resetting value or velocity
// to zero at the handoff: first run `steps1` calls to springStep (using the
// same formula as task 5) starting at rest (value = start, velocity = 0)
// toward `target1`. Then, WITHOUT snapping value or velocity, continue
// running `steps2` more springStep calls from exactly that ending state,
// now toward `target2`. Return the final AnimationState. This is the
// interruption/retarget rule real animation systems rely on, snapping
// either value or velocity at the handoff is the classic "animation jump"
// bug.
fun runInterruptedSpring(
    start: Float,
    target1: Float,
    steps1: Int,
    target2: Float,
    steps2: Int,
    stiffness: Float,
    damping: Float,
    dt: Float
): AnimationState {
    TODO("t6: run steps1 toward target1 from rest, then steps2 toward target2 continuing from that exact value/velocity")
}
