package toby.refactoring.ch1

import java.text.NumberFormat
import java.util.*

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    return renderPlainText(invoice, plays)
}

fun renderPlainText(invoice: Invoice, plays: Map<String, Play>): String {
    fun playFor(performance: Performance): Play {
        return plays[performance.playID]!!
    }

    fun amountFor(performance: Performance): Int {
        var result: Int

        when (playFor(performance).type) {
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
                throw IllegalArgumentException("Unknown type: ${playFor(performance).type}")
            }
        }
        return result
    }

    fun volumeCreditsFor(performance: Performance): Int {
        var result = 0
        result += maxOf(performance.audience - 30, 0)
        if ("comedy" == playFor(performance).type) result += performance.audience / 5
        return result
    }

    fun usd(amount: Int): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        return format.format(amount / 100.0)
    }

    fun totalVolumeCredits(): Int {
        var result = 0
        invoice.performances.forEach { perf ->
            result += volumeCreditsFor(perf)
        }
        return result
    }

    fun totalAmount(): Int {
        var result = 0
        invoice.performances.forEach { perf ->
            result += amountFor(perf)
        }
        return result
    }

    var result = "Statement for ${invoice.customer}\n"
    invoice.performances.forEach { perf ->
        result += "  ${playFor(perf).name}: ${usd(amountFor(perf))} (${perf.audience} seats)\n"
    }

    result += "Amount owed is ${usd(totalAmount())}\n"
    result += "You earned ${totalVolumeCredits()} credits\n"
    return result
}

fun main() {
    val invoice = invoices[0]
    val result = statement(invoice, plays)
    println(result)
}