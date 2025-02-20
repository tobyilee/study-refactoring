package toby.refactoring.ch4

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.BeforeTest
import kotlin.test.Test

class ProvinceTest {
    lateinit var asia: Province

    @BeforeTest
    fun setUp() {
        asia = Province(sampleProvinceData)
    }

    @Test
    fun shortfall() {
        assertThat(asia.shortfall).isEqualTo(5)
    }

    @Test
    fun profit() {
        assertThat(asia.profit).isEqualTo(230)
    }

    @Test
    fun `change production`() {
        sampleProvinceData.copy(producers = sampleProvinceData.producers.toMutableList().apply {
            this[0] = sampleProvinceData.producers[0].copy(production = 20)
        }).let {
            val changeProduction = Province(it)
            assertThat(changeProduction.shortfall).isEqualTo(-6)
            assertThat(changeProduction.profit).isEqualTo(292)
        }
    }

    @Test
    fun `zero demand`() {
        val data = sampleProvinceData.copy(demand = 0)
        val noDemand = Province(data)
        assertThat(noDemand.shortfall).isEqualTo(-25)
        assertThat(noDemand.profit).isEqualTo(0)
    }

    @Test
    fun `negative demand`() {
        val data = sampleProvinceData.copy(demand = -1)
        val negativeDemand = Province(data)
        assertThat(negativeDemand.shortfall).isEqualTo(-26)
        assertThat(negativeDemand.profit).isEqualTo(-10)
    }
}