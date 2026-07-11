package mvvm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: business logic (the total) stays separate from UI logic (how it's shown).
class T2CartUiStateTest {
    @Test fun `formats a total under a dollar`() {
        val items = listOf(CartItem("Sticker", priceCents = 50, quantity = 1))
        assertEquals("$0.50", cartUiState(items).totalLabel)
    }

    @Test fun `formats a total with cents`() {
        val items = listOf(
            CartItem("Pen", priceCents = 150, quantity = 2),
            CartItem("Book", priceCents = 999, quantity = 1)
        )
        assertEquals("$12.99", cartUiState(items).totalLabel)
    }

    @Test fun `formats a whole dollar amount with two zero cents`() {
        val items = listOf(CartItem("Mug", priceCents = 500, quantity = 1))
        assertEquals("$5.00", cartUiState(items).totalLabel)
    }

    @Test fun `keeps the items unchanged and reports zero for an empty cart`() {
        val state = cartUiState(emptyList())
        assertEquals(emptyList<CartItem>(), state.items)
        assertEquals("$0.00", state.totalLabel)
    }
}
