package extensions

import org.junit.jupiter.api.Assertions.fail
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

// Tasks 1-4 are declared by the learner from scratch, so these tests can't
// mention them by name at compile time. Instead they look the declaration up
// on the compiled file: top-level extensions in Tasks.kt compile to STATIC
// methods on the class `extensions.TasksKt`, with the receiver passed as the
// first parameter (an extension property `val String.wordCount` becomes
// `getWordCount(String)`). That's not a test trick, it's what an extension IS
// under the hood - worth a look with Tools > Kotlin > Show Kotlin Bytecode.

internal fun findExtension(jvmName: String, vararg params: Class<*>): Method? =
    runCatching { Class.forName("extensions.TasksKt") }.getOrNull()
        ?.methods
        ?.find { it.name == jvmName && it.parameterTypes.contentEquals(params) }

internal fun notDeclaredYet(task: String, what: String): Nothing =
    fail("$task isn't declared yet (or its signature doesn't match). $what")

// Invoke a static extension method, unwrapping the reflection wrapper so a
// thrown IndexOutOfBoundsException reaches assertThrows as itself.
internal fun Method.call(vararg args: Any?): Any? =
    try {
        invoke(null, *args)
    } catch (e: InvocationTargetException) {
        throw e.cause ?: e
    }
