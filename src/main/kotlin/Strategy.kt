package org.example.strategy

interface DeliveryMethod{
    fun calculateCost(distance: Int):Int
}

class DeliveryCar : DeliveryMethod{
    override fun calculateCost(distance: Int):Int{
        val baseCost = 200
        println("This is a car delivery man. and they want:")
        return baseCost * distance
    }
}

class DeliveryAirplane : DeliveryMethod{
    override fun calculateCost(distance: Int):Int{
        val baseCost = 100
        println("This is an air company. and they want:")
        return baseCost * distance
    }
}

class DeliveryCargo : DeliveryMethod{
    override fun calculateCost(distance: Int):Int{
        val baseCost = 50
        println("This is a cargo company. and they want:")
        return baseCost * distance
    }
}

class DeliveryMan : DeliveryMethod{
    override fun calculateCost(distance: Int): Int{
        val baseCost = 300
        println("This is a delivery man. and he wants:")
        return baseCost*distance
    }
}

fun main() {

    var mail: DeliveryMethod
    var distance: Int

    mail = DeliveryMan()
    distance = 10
    println(mail.calculateCost(distance))

    mail = DeliveryCar()
    distance = 200
    println(mail.calculateCost(distance))

    mail = DeliveryCargo()
    distance = 8000
    println(mail.calculateCost(distance))

    mail = DeliveryAirplane()
    distance = 4000
    println(mail.calculateCost(distance))


}