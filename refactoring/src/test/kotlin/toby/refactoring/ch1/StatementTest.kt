package toby.refactoring.ch1

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class StatementTest {
    @Test
    fun statement() {
        val invoice = invoices[0]
        val result = statement(invoice, plays)

        Assertions.assertThat(result).isEqualTo(
            """
            Statement for BigCo
              Hamlet: $650.00 (55 seats)
              As You Like It: $580.00 (35 seats)
              Othello: $500.00 (40 seats)
            Amount owed is $1,730.00
            You earned 47 credits
            
            """.trimIndent()
        )
    }

}