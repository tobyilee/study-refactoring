package toby.refactoring.ch1

data class StatementData(
    val customer: String,
    val performances: List<EnrichedPerformance>,
) {
    var totalAmount: Int = 0
    var totalVolumeCredits: Int = 0
}

class EnrichedPerformance(
    val playID: String,
    val audience: Int,
    val play: Play,

    ) {
    var amount: Int = 0 // lateinit을 쓰고 싶지만 primitive type이라서 안됨
    var volumeCredits: Int = 0
}

fun createStatementData(invoice: Invoice, plays: Map<String, Play>): StatementData {
    fun playFor(performance: Performance): Play {
        return plays[performance.playID]!!
    }

    fun amountFor(performance: EnrichedPerformance): Int {
        var result: Int

        when (performance.play.type) {
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
                throw IllegalArgumentException("Unknown type: ${performance.play}")
            }
        }
        return result
    }

    fun volumeCreditsFor(performance: EnrichedPerformance): Int {
        var result = 0
        result += maxOf(performance.audience - 30, 0)
        if ("comedy" == performance.play.type) result += performance.audience / 5
        return result
    }

    fun enrichPerformance(performance: Performance): EnrichedPerformance {
        return EnrichedPerformance(performance.playID, performance.audience, playFor(performance)).apply {
            amount = amountFor(this)
            volumeCredits = volumeCreditsFor(this)
        }
    }

    fun totalVolumeCredits(data: StatementData): Int {
        return data.performances.fold(0) { total, perf ->
            total + perf.volumeCredits
        }
    }

    fun totalAmount(data: StatementData): Int {
        return data.performances.fold(0) { total, perf ->
            total + perf.amount
        }
    }

    return StatementData(invoice.customer, invoice.performances.map { enrichPerformance(it) }).apply {
        totalAmount = totalAmount(this)
        totalVolumeCredits = totalVolumeCredits(this)
    }
}
