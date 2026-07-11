package delegation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: standard vetoable delegate rejecting invalid writes.
class T4GameTest {
    @Test fun `starts at the initial value`() {
        assertEquals(0, Game().highScore)
    }

    @Test fun `accepts a higher value`() {
        val game = Game()
        game.highScore = 100
        assertEquals(100, game.highScore)
    }

    @Test fun `rejects a lower value, keeping the previous one`() {
        val game = Game()
        game.highScore = 100
        game.highScore = 50
        assertEquals(100, game.highScore)
    }

    @Test fun `rejects an equal value too`() {
        val game = Game()
        game.highScore = 100
        game.highScore = 100
        assertEquals(100, game.highScore)
    }
}
