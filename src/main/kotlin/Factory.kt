package org.example.factory

interface Api{
    fun send(data: String)
}

class ApiClient : Api{
    override fun send(data: String) {
        println(data)
    }
}

class Logger(private val api: Api) : Api{
    override fun send(data: String) {
        println("This is start")
        api.send(data)
        println("This is end")
    }
}

class NetworkCheck(private val api: Api) : Api{
    override fun send(data: String) {
        val networkStatus = true

        if (networkStatus) {
            println("Network is online")
            api.send(data)
        }
        else{
            println("Network is offline")
        }
    }
}

object ApiFactory {
    fun create(
        isDebug: Boolean,
    ) : Api{
        val api = ApiClient()
        if (isDebug) {
            val logger = Logger(api)
            val networkCheck = NetworkCheck(logger)
            return networkCheck
        }
        else{
            val networkCheck = NetworkCheck(api)
            return networkCheck
        }
    }
}

fun main() {
    val api = ApiFactory.create(isDebug = false)

    val data = "Some data"

    api.send(data)


    val api2 = ApiFactory.create(isDebug = true)
    val data2 = "Some data^2"
    api2.send(data2)
}