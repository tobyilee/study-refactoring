package toby.refactoring.ch1

import java.text.NumberFormat
import java.util.*

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    var totalAmount = 0
    var volumeCredits = 0

    var result = "Statement for ${invoice.customer}\n"
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

    invoice.performances.forEach { perf ->
        val play = plays[perf.playID]!!
        val thisAmount: Int = amountFor(play, perf)

        // add volume credits
        volumeCredits += maxOf(perf.audience - 30, 0)
        // add extra credit for every ten comedy attendees
        if ("comedy" == play.type) volumeCredits += perf.audience / 5

        // print line for this order
        result += "  ${play.name}: ${format.format(thisAmount / 100.0)} (${perf.audience} seats)\n"
        totalAmount += thisAmount
    }
    result += "Amount owed is ${format.format(totalAmount / 100.0)}\n"
    result += "You earned $volumeCredits credits\n"
    return result
}

private fun amountFor(play: Play, perf: Performance): Int {
    var thisAmount: Int

    when (play.type) {
        "tragedy" -> {
            thisAmount = 40000
            if (perf.audience > 30) {
                thisAmount += 1000 * (perf.audience - 30)
            }
        }

        "comedy" -> {
            thisAmount = 30000
            if (perf.audience > 20) {
                thisAmount += 10000 + 500 * (perf.audience - 20)
            }
            thisAmount += 300 * perf.audience
        }

        else -> {
            throw IllegalArgumentException("Unknown type: ${play.type}")
        }
    }
    return thisAmount
}

fun main() {
    val invoice = invoices[0]
    val result = statement(invoice, plays)
    println(result)
}