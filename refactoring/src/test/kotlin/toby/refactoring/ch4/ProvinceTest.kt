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

}