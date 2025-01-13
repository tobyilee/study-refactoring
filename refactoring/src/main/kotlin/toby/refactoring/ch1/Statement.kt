package toby.refactoring.ch1

import java.text.NumberFormat
import java.util.*

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    fun playFor(performance: Performance): Play {
        return plays[performance.playID]!!
    }

    fun amountFor(play: Play, performance: Performance): Int {
        var result: Int

        when (play.type) {
            "tragedy" -> {
                result = 40000
                if (performance.audience > 30) {
                    result += 1000 * (performance.audience - 30)
                }
            }

            "comedy" -> {
                result = 30000
                if (performance.audience > 20) {
                    result += 10000 + 500 * (performance.audience - 20)
                }
                result += 300 * performance.audience
            }

            else -> {
                throw IllegalArgumentException("Unknown type: ${play.type}")
            }
        }
        return result
    }

    var totalAmount = 0
    var volumeCredits = 0

    var result = "Statement for ${invoice.customer}\n"
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

    invoice.performances.forEach { perf ->
        val thisAmount: Int = amountFor(playFor(perf), perf)

        // add volume credits
        volumeCredits += maxOf(perf.audience - 30, 0)
        // add extra credit for every ten comedy attendees
        if ("comedy" == playFor(perf).type) volumeCredits += perf.audience / 5

        // print line for this order
        result += "  ${playFor(perf).name}: ${format.format(thisAmount / 100.0)} (${perf.audience} seats)\n"
        totalAmount += thisAmount
    }
    result += "Amount owed is ${format.format(totalAmount / 100.0)}\n"
    result += "You earned $volumeCredits credits\n"
    return result
}


fun main() {
    val invoice = invoices[0]
    val result = statement(invoice, plays)
    println(result)
}