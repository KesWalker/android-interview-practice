package expectactual

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 3: the factory that resolves the right "actual" per platform token.
class T3ProvidePlatformStoreTest {
    @Test fun `ANDROID resolves to an AndroidStore`() {
        assertTrue(providePlatformStore(Platform.ANDROID) is AndroidStore)
    }

    @Test fun `IOS resolves to an IosStore`() {
        assertTrue(providePlatformStore(Platform.IOS) is IosStore)
    }

    @Test fun `resolved store is fully usable`() {
        val store = providePlatformStore(Platform.ANDROID)
        store.write("k", "v")
        assertEquals("v", store.read("k"))
    }
}
