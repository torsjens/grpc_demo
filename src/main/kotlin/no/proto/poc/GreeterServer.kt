package no.proto.poc

import io.grpc.ServerBuilder

fun main() {
    val server = GreeterServer(8080)
    server.start()
    server.blockUntilShutdown()
}

class GreeterServer(private val port: Int) {

    val server = ServerBuilder.forPort(port)
        .addService(GreeterServiceImpl())
        .build()

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("Shutting down gRPC server since JVM is shutting down")
                this@GreeterServer.stop()
                println("Server shut down")
            },
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

}