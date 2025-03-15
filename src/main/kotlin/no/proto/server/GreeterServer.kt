package no.proto.server

import io.grpc.Grpc
import io.grpc.InsecureServerCredentials
import io.grpc.Server
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class GreeterServer {

    private lateinit var server: Server

    private fun start(port: Int) {
        val executor = Executors.newFixedThreadPool(2)
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
            .executor(executor)
            .addService(GreeterServiceImpl())
            .build()
            .start()



    }

    private fun stop() {
        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }

    private fun blockUntilShutdown() {
        server.awaitTermination()
    }

    fun main(args: Array<String>) {
        val server = GreeterServer()
        server.start(50051)
        server.blockUntilShutdown()
    }

}