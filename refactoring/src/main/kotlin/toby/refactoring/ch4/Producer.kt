package toby.refactoring.ch4

class Producer(
    private val province: Province,
    private val data: ProducerData,
) {
    val name: String = data.name
    var cost: Int = data.cost
    var production: Int = data.production

    fun setProduction(amountStr: String) {
        val newProduction = kotlin.runCatching { amountStr.toInt() }.getOrElse { 0 }
        this.province.totalProduction += newProduction - this.production
        this.production = newProduction
    }

}
