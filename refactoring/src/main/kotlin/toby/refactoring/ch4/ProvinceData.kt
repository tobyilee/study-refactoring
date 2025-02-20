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
    val production: Int,
)

val sampleProvinceData = ProvinceData(
    name = "Asia",
    producers = listOf(
        ProducerData("Byzantium", 10, 9),
        ProducerData("Attalia", 12, 10),
        ProducerData("Sinope", 10, 6),
    ),
    demand = 30,
    price = 20,
)