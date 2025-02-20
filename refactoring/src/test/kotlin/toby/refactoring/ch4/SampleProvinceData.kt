package toby.refactoring.ch4

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