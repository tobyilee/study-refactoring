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

    fun enrichPerformance(performance: Performance): EnrichedPerformance {
        val calculator = PerformanceCalculator(
            performance,
            playFor(performance)
        )
        return EnrichedPerformance(
            performance.playID,
            performance.audience,
            calculator.play
        ).apply {
            amount = calculator.amount()
            volumeCredits = calculator.volumeCredits()
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

class PerformanceCalculator(val performance: Performance, var play: Play) {
    fun amount(): Int {
        var result: Int

        when (this.play.type) {
            "tragedy" -> {
                result = 40000
                if (this.performance.audience > 30) {
                    result += 1000 * (performance.audience - 30)
                }
            }

            "comedy" -> {
                result = 30000
                if (this.performance.audience > 20) {
                    result += 10000 + 500 * (performance.audience - 20)
                }
                result += 300 * this.performance.audience
            }

            else -> {
                throw IllegalArgumentException("Unknown type: ${this.play.type}")
            }
        }
        return result
    }

    fun volumeCredits(): Int {
        var result = 0
        result += maxOf(this.performance.audience - 30, 0)
        if ("comedy" == this.play.type) result += this.performance.audience / 5
        return result
    }
}
