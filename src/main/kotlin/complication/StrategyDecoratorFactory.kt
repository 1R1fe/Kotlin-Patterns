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
        return inner.calculateCost(distance) + 100.00
    }
}

class FastDelivery(val inner: DeliveryMethod) : DeliveryMethod{
    override fun calculateCost(distance: Int):Double {
        return inner.calculateCost(distance) + 50.00
    }
}

class Fragile(val inner: DeliveryMethod):DeliveryMethod{
    override fun calculateCost(distance: Int):Double {
        return inner.calculateCost(distance) + 20.00
    }
}
class Dangerous(val inner: DeliveryMethod):DeliveryMethod {
    override fun calculateCost(distance: Int): Double {
        return inner.calculateCost(distance) + 10.00
    }
}

class Expensive(val inner: DeliveryMethod):DeliveryMethod{
    override fun calculateCost(distance: Int):Double {
        return inner.calculateCost(distance) + 15.00
    }
}

enum class Transport{
    Ship,
    DeliveryMan,
    Plane,
    Truck
}
enum class Category{
    Fragile,
    Dangerous,
    Expensive
}

object OrderFactory{
    fun create(
        fastDelivery: Boolean,
        insurance: Boolean,
        transport: Transport,
        category: Category
        ) : DeliveryMethod {

        var data: DeliveryMethod

        data = when (transport){
            Transport.Ship -> Ship()
            Transport.DeliveryMan -> DeliveryMan()
            Transport.Plane -> Plane()
            Transport.Truck -> Truck()
        }
        if (fastDelivery){
            data = FastDelivery(data)
        }
        if (insurance){
            data = Insurance(data)
        }
        data = when(category){
            Category.Fragile -> Fragile(data)
            Category.Dangerous -> Dangerous(data)
            Category.Expensive -> Expensive(data)
        }

        return data
    }
}

fun main(){
    var order = OrderFactory.create(
        fastDelivery = true,
        insurance = true,
        transport = Transport.Ship,
        category = Category.Fragile,
    )

    println(order.calculateCost(1000))

    order = OrderFactory.create(
        fastDelivery = false,
        insurance = true,
        transport = Transport.DeliveryMan,
        category = Category.Expensive
    )

    println(order.calculateCost(1000))

}
