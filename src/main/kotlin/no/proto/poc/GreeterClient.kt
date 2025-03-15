package no.proto.poc

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import no.proto.generated.GreeterServiceGrpcKt
import no.proto.generated.helloRequest
import java.io.Closeable
import java.util.concurrent.TimeUnit

suspend fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 8080)
        .usePlaintext()
        .build()

    val client = GreeterClient(channel)

    client.greet("Torstein")
}

class GreeterClient(private val channel: ManagedChannel) : Closeable {

    private val stub: GreeterServiceGrpcKt.GreeterServiceCoroutineStub =
        GreeterServiceGrpcKt.GreeterServiceCoroutineStub(channel)

    suspend fun greet(name: String) {
        val request = helloRequest { this.name = name }
        val response = stub.sayHello(request)
        println("Received: ${response.message}")
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

}
