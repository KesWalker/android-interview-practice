package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: extension PROPERTY with an explicit getter. A property's getter
// compiles to getWordCount(String) - if you declared a function instead,
// you'll see "isn't declared yet" even though your code compiles.
class T2WordCountTest {
    private fun wordCount(receiver: String): Any? {
        val m = findExtension("getWordCount", String::class.java)
            ?: notDeclaredYet(
                "t2: wordCount",
                "Declare a top-level extension PROPERTY (val, not fun) on String, of " +
                    "type Int. It can't have a backing field, so it needs an explicit " +
                    "getter: `val String.wordCount: Int get() = ...`-shaped.",
            )
        return m.call(receiver)
    }

    @Test fun `empty string has no words`() = assertEquals(0, wordCount(""))
    @Test fun `blank string has no words`() = assertEquals(0, wordCount("   "))
    @Test fun `counts words separated by whitespace`() = assertEquals(3, wordCount("kotlin is fun"))
}
