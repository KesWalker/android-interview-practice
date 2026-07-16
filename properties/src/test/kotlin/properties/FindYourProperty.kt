package properties

import org.junit.jupiter.api.Assertions.fail
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

// Every property in this module is declared by the learner from scratch, so
// these tests can't mention one by name at compile time. They look it up on
// the class instead: a Kotlin property compiles to a getX()/setX() pair (a
// delegated property additionally keeps its delegate in an x$delegate field),
// so `val value` is found here as the instance method getValue().

internal fun Any.property(getterName: String): Method? =
    this::class.java.methods.find { it.name == getterName && it.parameterCount == 0 }

internal fun Any.setter(setterName: String, param: Class<*>): Method? =
    this::class.java.methods.find { it.name == setterName && it.parameterTypes.contentEquals(arrayOf(param)) }

internal fun notDeclaredYet(task: String, what: String): Nothing =
    fail("$task isn't declared yet (or its shape doesn't match). $what")

// Invoke through reflection, unwrapping the wrapper so the property's own
// exception (UninitializedPropertyAccessException, IllegalStateException)
// reaches assertThrows as itself.
internal fun Method.call(target: Any, vararg args: Any?): Any? =
    try {
        invoke(target, *args)
    } catch (e: InvocationTargetException) {
        throw e.cause ?: e
    }
