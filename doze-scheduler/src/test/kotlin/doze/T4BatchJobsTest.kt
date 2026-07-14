package doze

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: batching deferred jobs into shared windows is the whole battery win.
class T4BatchJobsTest {
    private val interval = 900_000L

    @Test fun `jobs landing in the same window share one radio wakeup`() {
        val jobs = listOf(
            PendingJob("a", arrivalMillis = 100_000L),
            PendingJob("b", arrivalMillis = 500_000L),
            PendingJob("c", arrivalMillis = 850_000L)
        )

        val result = batchJobs(jobs, interval)

        assertEquals(
            listOf(
                ScheduledJob("a", interval),
                ScheduledJob("b", interval),
                ScheduledJob("c", interval)
            ),
            result.scheduled
        )
        assertEquals(2, result.radioWakeupsSaved)
    }

    @Test fun `jobs already spread across windows save nothing`() {
        val jobs = listOf(
            PendingJob("a", arrivalMillis = 0L),
            PendingJob("b", arrivalMillis = interval + 1)
        )

        val result = batchJobs(jobs, interval)

        assertEquals(
            listOf(
                ScheduledJob("a", 0L),
                ScheduledJob("b", 2 * interval)
            ),
            result.scheduled
        )
        assertEquals(0, result.radioWakeupsSaved)
    }

    @Test fun `empty queue batches to nothing`() {
        val result = batchJobs(emptyList(), interval)
        assertEquals(emptyList<ScheduledJob>(), result.scheduled)
        assertEquals(0, result.radioWakeupsSaved)
    }
}
