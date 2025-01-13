package toby.refactoring.ch1

import java.text.NumberFormat
import java.util.*


fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    return renderPlainText(createStatementData(invoice, plays))
}

fun htmlStatement(invoice: Invoice, plays: Map<String, Play>): String {
    return renderHtml(createStatementData(invoice, plays))
}

fun usd(amount: Int): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(amount / 100.0)
}

fun renderHtml(data: StatementData): String {
    var result = "<h1>Statement for ${data.customer}</h1>\n"
    result += "<table>\n"
    result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>"
    data.performances.forEach { perf ->
        result += "  <tr><td>${perf.play.name}</td><td>${perf.audience}</td>"
        result += "<td>${usd(perf.amount)}</td></tr>\n"
    }
    result += "</table>\n"
    result += "<p>Amount owed is <em>${usd(data.totalAmount)}</em></p>\n"
    result += "<p>You earned <em>${data.totalVolumeCredits}</em> credits</p>\n"
    return result
}

fun renderPlainText(data: StatementData): String {
    var result = "Statement for ${data.customer}\n"
    data.performances.forEach { perf ->
        result += "  ${perf.play.name}: ${usd(perf.amount)} (${perf.audience} seats)\n"
    }

    result += "Amount owed is ${usd(data.totalAmount)}\n"
    result += "You earned ${data.totalVolumeCredits} credits\n"
    return result
}

fun main() {
    val invoice = invoices[0]
    val result = statement(invoice, plays)
    println(result)
}