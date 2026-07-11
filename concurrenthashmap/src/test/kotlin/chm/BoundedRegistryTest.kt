package chm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

// Task 6: size()/isEmpty() are estimates; don't gate correctness on them.
class BoundedRegistryTest {
    @Test fun `registering under capacity sequentially all succeed`() {
        val registry = BoundedRegistry(3)

        assertTrue(registry.register("a", "va"))
        assertTrue(registry.register("b", "vb"))
        assertTrue(registry.register("c", "vc"))
        assertEquals(3, registry.size())
    }

    @Test fun `registering past capacity is rejected`() {
        val registry = BoundedRegistry(1)

        assertTrue(registry.register("a", "va"))
        assertEquals(false, registry.register("b", "vb"))
        assertEquals(1, registry.size())
    }

    @Test fun `500 threads racing against capacity 100 land exactly 100 successes`() {
        val registry = BoundedRegistry(100)
        val successes = AtomicInteger(0)
        val pool = Executors.newFixedThreadPool(16)
        val tasks = (0 until 500).map { i ->
            Callable { if (registry.register("id$i", "v$i")) successes.incrementAndGet() }
        }
        pool.invokeAll(tasks)
        pool.shutdown()

        assertEquals(100, successes.get())
        assertEquals(100, registry.size())
    }
}
