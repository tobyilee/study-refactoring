package toby.refactoring.ch4

data class ProvinceData(
    val name: String,
    val producers: List<ProducerData>,
    val demand: Int,
    val price: Int,
)

data class ProducerData(
    val name: String,
    val cost: Int,
    var production: Int,
)

