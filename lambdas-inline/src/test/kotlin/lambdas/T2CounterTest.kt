package lambdas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: a closure capturing and mutating an enclosing variable.
class T2CounterTest {
    @Test fun `first call returns one`() {
        val counter = makeCounter()
        assertEquals(1, counter())
    }

    @Test fun `increments on each call`() {
        val counter = makeCounter()
        counter()
        counter()
        assertEquals(3, counter())
    }

    @Test fun `independent counters do not share state`() {
        val a = makeCounter()
        val b = makeCounter()
        a()
        a()
        assertEquals(1, b())
    }
}
