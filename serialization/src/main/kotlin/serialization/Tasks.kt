package serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonNames

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

// TODO(t1): T1EncodeCardTest
// Convert a Card into its JSON text.
fun encodeCard(card: Card): String {
    TODO("t1: return the JSON string for this card")
}

@Serializable
data class Profile(val username: String, val age: Int)

// TODO(t2): T2DecodeLenientProfileTest
// Parse profile JSON that may contain fields this app doesn't declare, without failing.
fun decodeLenientProfile(json: String): Profile {
    TODO("t2: parse json into a Profile even when it carries fields Profile doesn't know about")
}

@Serializable
data class Preferences(val theme: String = "light", val notificationsEnabled: Boolean = true)

// TODO(t3): T3DecodePreferencesTest
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

// TODO(t4): T4DecodeShapesTest
// Parse a JSON array where each element carries a "type" field into a list of the matching shape instances.
fun decodeShapes(json: String): List<Shape> {
    TODO("t4: parse json into a List<Shape>, picking the right subtype per element")
}

@Serializable
data class Account(
    @SerialName("username")
    @OptIn(ExperimentalSerializationApi::class)
    @JsonNames("login", "user_name")
    val name: String,
)

// TODO(t5): T5DecodeAccountTest
// Parse account JSON into an Account whose Kotlin property has a renamed
// canonical JSON key, and that still accepts an older key name some clients
// may still send.
fun decodeAccount(json: String): Account {
    TODO("t5: parse json into an Account, accepting the canonical key or a legacy alias")
}

@Serializable
data class Session(val userId: String, @Transient val debugToken: String = "none")

// TODO(t6): T6EncodeSessionTest
// Encode a Session so its @Transient field never appears in the JSON at all,
// regardless of the value it holds.
fun encodeSession(session: Session): String {
    TODO("t6: return the JSON string for this session, without the transient field")
}

@Serializable
data class Settings(val fontScale: Double = 1.0)

// TODO(t7): T7DecodeSettingsLenientTest
// Parse settings JSON that may send an explicit null for a non-nullable field
// with a default, falling back to that default instead of throwing.
fun decodeSettingsLenient(json: String): Settings {
    TODO("t7: parse json into Settings, falling back to the default on an explicit null")
}

// TODO(t8): T8CentsSerializerTest
// Write a custom KSerializer for a Cents value class so it reads and writes as
// a plain decimal string like "12.34" instead of the default numeric
// representation.
@Serializable(with = CentsSerializer::class)
@JvmInline
value class Cents(val amount: Long)

@Serializable
data class Price(val amount: Cents)

object CentsSerializer : KSerializer<Cents> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Cents", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Cents) {
        TODO("t8: write the cents out as a two-decimal string")
    }

    override fun deserialize(decoder: Decoder): Cents {
        TODO("t8: read the two-decimal string back into a Cents amount")
    }
}
