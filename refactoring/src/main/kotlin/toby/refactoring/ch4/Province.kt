package toby.refactoring.ch4

import kotlin.math.min

class Province(
    doc: ProvinceData
) {
    val name: String = doc.name
    var totalProduction: Int = 0
    val demand: Int = doc.demand
    val price: Int = doc.price

    private val producers: MutableList<Producer> = mutableListOf()

    init {
        doc.producers.forEach {
            addProducer(Producer(this, it))
        }
    }

    private fun addProducer(data: Producer, ) {
        producers.add(data)
        totalProduction += data.production
    }

    val shortfall: Int
        get() = demand - totalProduction

    val profit: Int
        get() = demandValue - demandCost

    val demandCost: Int
        get() {
            var remainingDemand = demand
            var result = 0
            producers.sortedBy { it.cost }.forEach {
                val contribution = min(remainingDemand, it.production)
                remainingDemand -= contribution
                result += contribution * it.cost
            }
            return result
        }

    val demandValue: Int
        get() = satisfiedDemand * price

    val satisfiedDemand: Int
        get() = min(demand, totalProduction)

}