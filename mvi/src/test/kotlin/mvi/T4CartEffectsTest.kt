package mvi

import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: a one-off effect delivered once, kept separate from state.
class T4CartEffectsTest {
    @Test fun `announces the item that was added`() = runTest {
        val cart = CartEffects()
        cart.addToCart("Headphones")
        val effect = cart.effects.take(1).toList().single()
        assertEquals(CartEffect.ShowToast("Headphones added to cart"), effect)
    }

    @Test fun `each call sends its own effect, in order`() = runTest {
        val cart = CartEffects()
        cart.addToCart("Mouse")
        cart.addToCart("Keyboard")
        val effects = cart.effects.take(2).toList()
        assertEquals(
            listOf(
                CartEffect.ShowToast("Mouse added to cart"),
                CartEffect.ShowToast("Keyboard added to cart")
            ),
            effects
        )
    }
}
