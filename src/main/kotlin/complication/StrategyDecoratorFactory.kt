package complication

//Combining decorator, strategy and simple factory patterns together.

//For example, delivery cost now depends on transport type and cargo category(dangerous, valuable, fragile)
//We also want to support add-on services:
//insurance - +10$ to price
//fast delivery - +50$ to price

interface DeliveryMethod{
    fun calculateCost(distance: Int): Double
}

class Ship : DeliveryMethod{
    override fun calculateCost(distance: Int):Double = distance * 2.0
}

class Plane : DeliveryMethod{
    override fun calculateCost(distance: Int):Double = distance * 4.0
}

class Truck : DeliveryMethod{
    override fun calculateCost(distance: Int):Double = distance * 6.0
}

class DeliveryMan : DeliveryMethod{
    override fun calculateCost(distance: Int):Double = distance * 10.0
}

class Insurance(val inner: DeliveryMethod):DeliveryMethod{
    override fun calculateCost(distance: Int):Double {
        println("Add-on service: Insurance")

        return inner.calculateCost(distance) + 100.00
    }
}
class FastDelivery(val inner: DeliveryMethod) : DeliveryMethod{
    override fun calculateCost(distance: Int):Double {
        println("Add-on service: fast delivery")

        return inner.calculateCost(distance) + 50.00
    }
}

object OrderFactory{
    fun create(
        fastDelivery: Boolean,
        insurance: Boolean,
        transport: DeliveryMethod,
        ) : DeliveryMethod {
        var order = transport

        if (fastDelivery){
            order = FastDelivery(order)
        }
        if (insurance){
            order = Insurance(order)
        }

        return order
    }
}

fun main(){
    var order = OrderFactory.create(
        fastDelivery = true,
        insurance = true,
        transport = DeliveryMan(),
    )
    println(order.calculateCost(1000))

    order = OrderFactory.create(
        fastDelivery = false,
        insurance = true,
        transport = Ship(),
    )
    println(order.calculateCost(1000))
}

//убрать логирование из декораторов.
//добавить классы для категорий товаров.