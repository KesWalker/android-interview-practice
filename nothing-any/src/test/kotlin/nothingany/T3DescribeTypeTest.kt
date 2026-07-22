package nothingany

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T3DescribeTypeTest {
    @Test fun `describes a String`() = assertEquals("text: hello", describeType("hello"))
    @Test fun `describes an Int`() = assertEquals("number: 42", describeType(42))
    @Test fun `describes a Double`() = assertEquals("decimal: 3.14", describeType(3.14))
    @Test fun `describes a Boolean as other`() = assertEquals("other: true", describeType(true))
    @Test fun `describes a List as other`() = assertEquals("other: [1, 2]", describeType(listOf(1, 2)))
}
