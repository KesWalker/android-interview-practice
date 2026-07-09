package serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * JSON Serialization practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to fill in
 * the body so its test goes GREEN. Run a single test class from the gutter in
 * Android Studio, or run them all with:
 *
 *     ./gradlew :serialization:test
 */

@Serializable
data class Card(val suit: String, val rank: Int)

// TODO(t1): EncodeCardTest
// Convert a Card into its JSON text.
fun encodeCard(card: Card): String {
    TODO("t1: return the JSON string for this card")
}

@Serializable
data class Profile(val username: String, val age: Int)

// TODO(t2): DecodeLenientProfileTest
// Parse profile JSON that may contain fields this app doesn't declare, without failing.
fun decodeLenientProfile(json: String): Profile {
    TODO("t2: parse json into a Profile even when it carries fields Profile doesn't know about")
}

@Serializable
data class Preferences(val theme: String = "light", val notificationsEnabled: Boolean = true)

// TODO(t3): DecodePreferencesTest
// Parse preferences JSON that might leave some fields out, still returning a usable Preferences.
fun decodePreferences(json: String): Preferences {
    TODO("t3: parse json into Preferences, filling in each field this json leaves out")
}

@Serializable
sealed interface Shape

@Serializable
@SerialName("circle")
data class Circle(val radius: Double) : Shape

@Serializable
@SerialName("square")
data class Square(val side: Double) : Shape

// TODO(t4): DecodeShapesTest
// Parse a JSON array where each element carries a "type" field into a list of the matching shape instances.
fun decodeShapes(json: String): List<Shape> {
    TODO("t4: parse json into a List<Shape>, picking the right subtype per element")
}
