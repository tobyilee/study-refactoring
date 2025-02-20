package toby.refactoring.ch4

import org.assertj.core.api.Assertions
import kotlin.test.BeforeTest
import kotlin.test.Test

class NoProducerTest {
    lateinit var noProducers: Province

    @BeforeTest
    fun setUp() {
        val data = ProvinceData(
            name = "No producers",
            producers = emptyList(),
            demand = 30,
            price = 20
        )
        noProducers = Province(data)
    }

    @Test
    fun shortfall() {
        Assertions.assertThat(noProducers.shortfall).isEqualTo(30)
    }

    @Test
    fun profit() {
        Assertions.assertThat(noProducers.profit).isEqualTo(0)
    }
}