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

    @Test
    fun htmlStatement() {
        val invoice = invoices[0]
        val result = htmlStatement(invoice, plays)

        Assertions.assertThat(result).isEqualTo(
            """
              <h1>Statement for BigCo</h1>
              <table>
              <tr><th>play</th><th>seats</th><th>cost</th></tr>  <tr><td>Hamlet</td><td>55</td><td>${'$'}650.00</td></tr>
                <tr><td>As You Like It</td><td>35</td><td>${'$'}580.00</td></tr>
                <tr><td>Othello</td><td>40</td><td>${'$'}500.00</td></tr>
              </table>
              <p>Amount owed is <em>${'$'}1,730.00</em></p>
              <p>You earned <em>47</em> credits</p>
              
            """.trimIndent()
        )
    }


}