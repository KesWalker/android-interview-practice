package classes

/**
 * Data, Sealed & Value Classes practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement the function so
 * its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :kotlin-classes:test
 *
 * Solutions are short - most are one or two lines.
 */

// A record with one property in its primary constructor and one property in
// its body.
data class Employee(val id: String) {
    var department: String = ""
}

// TODO(t1): T1EmployeeEqualityTest
// Return whether `a` and `b` should be treated as the same employee record.
fun sameEmployee(a: Employee, b: Employee): Boolean {
    TODO("t1: decide equality for two Employee records")
}

// A point on a 2D grid.
data class Point(val x: Int, val y: Int)

// TODO(t2): T2MovedPointTest
// Return a new Point shifted by dx/dy, leaving `point` itself untouched.
fun moved(point: Point, dx: Int, dy: Int): Point {
    TODO("t2: produce a Point shifted by dx/dy")
}

// The lifecycle of an order, one case per stage, each carrying its own data.
sealed interface DeliveryStatus
data class Placed(val orderId: String) : DeliveryStatus
data class Shipped(val trackingCode: String) : DeliveryStatus
data class Delivered(val signedBy: String) : DeliveryStatus

// TODO(t3): T3DeliveryStatusMessageTest
// Return a customer-facing message for every possible DeliveryStatus:
// Placed("A1") -> "Order A1 has been placed."
// Shipped("TRK9") -> "Your order is on the way (tracking: TRK9)."
// Delivered("Sam") -> "Delivered, signed by Sam."
fun statusMessage(status: DeliveryStatus): String {
    TODO("t3: cover every DeliveryStatus case with its message")
}

// A distance in meters.
@JvmInline
value class Meters(val value: Double)

// TODO(t4): T4DoubleDistanceTest
// Return a new Meters distance that's twice the given one.
fun doubled(distance: Meters): Meters {
    TODO("t4: return a Meters distance twice as large as `distance`")
}

// TODO(t5): T5ToPairTest
// Destructure `point` into its x and y components and return them as a
// Pair<Int, Int>, in (x, y) order.
fun toPair(point: Point): Pair<Int, Int> {
    TODO("t5: destructure the Point and return its components as an (x, y) pair")
}

// A team with a name and a mutable list of members.
data class Team(val name: String, val members: MutableList<String>)

// TODO(t6): T6RenamedWithIndependentMembersTest
// Return a copy of `team` with the new name, but make sure mutating the copy's
// members list afterward never affects the original team's list.
fun renamedWithIndependentMembers(team: Team, newName: String): Team {
    TODO("t6: copy the team with the new name and a members list independent of the original's")
}

// A traffic light, one constant per state, none carrying its own data.
enum class TrafficLight { RED, GREEN, YELLOW }

// TODO(t7): T7NextLightTest
// Advance the traffic light to its next state: RED -> GREEN -> YELLOW -> RED.
fun nextLight(current: TrafficLight): TrafficLight {
    TODO("t7: return the state that follows `current`")
}
