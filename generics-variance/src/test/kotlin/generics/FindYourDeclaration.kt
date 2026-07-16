package generics

import org.junit.jupiter.api.Assertions.fail
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

// Tasks 2, 3 and 5 are declared by the learner from scratch, so their tests
// can't mention them by name at compile time. Top-level functions in Tasks.kt
// compile to static methods on `generics.TasksKt`; these helpers look them up
// there and fail with a pointer when they don't exist yet.

internal fun findDeclaration(name: String, paramCount: Int): Method? =
    runCatching { Class.forName("generics.TasksKt") }.getOrNull()
        ?.methods
        ?.find { it.name == name && it.parameterCount == paramCount }

internal fun notDeclaredYet(task: String, what: String): Nothing =
    fail("$task isn't declared yet (or its shape doesn't match). $what")

internal fun Method.call(vararg args: Any?): Any? =
    try {
        invoke(null, *args)
    } catch (e: InvocationTargetException) {
        throw e.cause ?: e
    }
