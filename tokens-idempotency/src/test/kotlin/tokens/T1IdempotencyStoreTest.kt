package tokens

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

// Task 1: idempotency key dedupe.
class T1IdempotencyStoreTest {
    @Test fun `runs the operation the first time`() {
        val store = IdempotencyStore<String>()
        val result = store.execute("key-1") { "charged" }
        assertEquals("charged", result)
    }

    @Test fun `returns the cached result on a repeat with the same key without re-running`() {
        val store = IdempotencyStore<Int>()
        val calls = AtomicInteger(0)
        val op = { calls.incrementAndGet() }

        val first = store.execute("payment-42", op)
        val second = store.execute("payment-42", op)

        assertEquals(first, second)
        assertEquals(1, calls.get())
    }

    @Test fun `different keys each run their own operation`() {
        val store = IdempotencyStore<String>()
        val a = store.execute("key-a") { "A" }
        val b = store.execute("key-b") { "B" }
        assertEquals("A", a)
        assertEquals("B", b)
    }
}
