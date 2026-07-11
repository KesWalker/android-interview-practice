package repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: exposing ongoing changes as a Flow rather than a one-shot suspend function.
class ObserveArticlesByQueryTest {
    @Test fun `an initial saveAll is reflected in the first emission`() = runTest {
        val local = LocalArticleStore()
        local.saveAll(listOf(Article("1", "Kotlin Basics"), Article("2", "Swift Basics")))

        val first = observeArticlesByQuery(local, "kotlin").first()

        assertEquals(listOf(Article("1", "Kotlin Basics")), first)
    }

    @Test fun `a later saveAll produces a second, differently-filtered emission`() = runTest {
        val local = LocalArticleStore()
        local.saveAll(listOf(Article("1", "Kotlin Basics")))
        val emissions = mutableListOf<List<Article>>()
        val job = launch {
            observeArticlesByQuery(local, "kotlin").collect { emissions.add(it) }
        }
        runCurrent()

        local.saveAll(listOf(Article("2", "Kotlin Advanced"), Article("3", "Java Basics")))
        runCurrent()

        job.cancel()
        assertEquals(2, emissions.size)
        assertEquals(listOf(Article("1", "Kotlin Basics")), emissions[0])
        assertEquals(listOf(Article("2", "Kotlin Advanced")), emissions[1])
    }
}
