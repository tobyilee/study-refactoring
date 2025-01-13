package toby.refactoring.ch1

data class StatementData(
    val customer: String,
    val performances: List<EnrichedPerformance>,
) {
    val totalAmount: Int = totalAmount()
    val totalVolumeCredits: Int = totalVolumeCredits()

    private fun totalAmount(): Int {
        return this.performances.fold(0) { total, perf ->
            total + perf.amount
        }
    }

    private fun totalVolumeCredits(): Int {
        return this.performances.fold(0) { total, perf ->
            total + perf.volumeCredits
        }
    }

    companion object {
        fun create(invoice: Invoice, plays: Map<String, Play>): StatementData =
            StatementData(invoice.customer, invoice.performances.map { EnrichedPerformance.create(it, plays) })
    }
}

data class EnrichedPerformance(
    val playID: String,
    val audience: Int,
    val play: Play,
    var amount: Int,
    var volumeCredits: Int,
) {
    companion object {
        fun create(performance: Performance, plays: Map<String, Play>) =
            PerformanceCalculator.create(performance, plays[performance.playID]!!)
                .let { calculator ->
                    EnrichedPerformance(
                        performance.playID,
                        performance.audience,
                        calculator.play,
                        calculator.amount(),
                        calculator.volumeCredits()
                    )
                }
    }
}

abstract class PerformanceCalculator(val performance: Performance, val play: Play) {
    abstract fun amount(): Int

    open fun volumeCredits(): Int {
        return maxOf(this.performance.audience - 30, 0)
    }

    companion object {
        fun create(performance: Performance, play: Play): PerformanceCalculator {
            return when (play.type) {
                "tragedy" -> TragedyCalculator(performance, play)
                "comedy" -> ComedyCalculator(performance, play)
                else -> throw IllegalArgumentException("unknown type: ${play.type}")
            }
        }
    }
}

class TragedyCalculator(performance: Performance, play: Play) : PerformanceCalculator(performance, play) {
    override fun amount(): Int {
        var result = 40000
        if (this.performance.audience > 30) {
            result += 1000 * (performance.audience - 30)
        }
        return result
    }
}

class ComedyCalculator(performance: Performance, play: Play) : PerformanceCalculator(performance, play) {
    override fun amount(): Int {
        var result = 30000
        if (this.performance.audience > 20) {
            result += 10000 + 500 * (performance.audience - 20)
        }
        result += 300 * this.performance.audience
        return result
    }

    override fun volumeCredits(): Int {
        return super.volumeCredits() + (this.performance.audience / 5)
    }
}
