package toby.refactoring.ch4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProvinceTest {
    @Test
    fun shortfall() {
        val asia = Province(sampleProvinceData)

        assertThat(asia.shortfall).isEqualTo(5)
    }

    @Test
    fun profit() {
        val asia = Province(sampleProvinceData)

        assertThat(asia.profit).isEqualTo(230)
    }

}