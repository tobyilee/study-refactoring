package toby.refactoring.ch1

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

private val playJson = """
    {
      "hamlet": {"name": "Hamlet", "type": "tragedy"},
      "as-like": {"name": "As You Like It", "type": "comedy"},
      "othello": {"name": "Othello", "type": "tragedy"}
    }
""".trimIndent()

private val invoiceJson = """
    [
      {
        "customer": "BigCo",
        "performances": [
          {
            "playID": "hamlet",
            "audience": 55
          },
          {
            "playID": "as-like",
            "audience": 35
          },
          {
            "playID": "othello",
            "audience": 40
          }
        ]
      }
    ]
""".trimIndent()

@Serializable
data class Play(
    val name: String,
    val type: String
)

@Serializable
data class Performance(
    val playID: String,
    val audience: Int
)

@Serializable
data class Invoice(
    val customer: String,
    val performances: List<Performance>
)

private val json = Json { ignoreUnknownKeys = true }

val plays = json.decodeFromString<Map<String, Play>>(playJson)
val invoices = json.decodeFromString<List<Invoice>>(invoiceJson)

fun main() {
    println(plays)
    println(invoices)
}