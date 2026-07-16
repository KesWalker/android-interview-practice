package lambdas

import org.junit.jupiter.api.Assertions.fail
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import kotlin.reflect.jvm.kotlinFunction

// Tasks 5 and 6 are declared by the learner from scratch, so their tests can't
// mention them by name at compile time. Top-level functions in Tasks.kt
// compile to static methods on `lambdas.TasksKt`; these helpers look them up
// there. Inline functions still emit a real method (that's what Java callers
// use), so a non-reified inline function is perfectly invocable this way -
// and its `inline` modifier is readable back through Kotlin metadata.

internal fun findDeclaration(name: String, paramCount: Int): Method? =
    runCatching { Class.forName("lambdas.TasksKt") }.getOrNull()
        ?.methods
        ?.find { it.name == name && it.parameterCount == paramCount }

internal fun notDeclaredYet(task: String, what: String): Nothing =
    fail("$task isn't declared yet (or its shape doesn't match). $what")

internal fun Method.requireInline(task: String) {
    val kf = kotlinFunction ?: return
    if (!kf.isInline) {
        fail<Nothing>(
            "$task is declared but not INLINE. Without `inline` the lambda parameters are " +
                "plain objects and the task's modifiers never come into play - add `inline` " +
                "and let the compiler show you what breaks next.",
        )
    }
}

internal fun Method.call(vararg args: Any?): Any? =
    try {
        invoke(null, *args)
    } catch (e: InvocationTargetException) {
        throw e.cause ?: e
    }
