package org.example.decorator

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
            api.send(data)
        }
        else{
            println("Network is offline")
        }
    }
}

fun main() {
    val apiclient = ApiClient()
    val logger = Logger(apiclient)
    val networkCheck = NetworkCheck(logger)

    val data = "Sasha lox"

    networkCheck.send(data)
}