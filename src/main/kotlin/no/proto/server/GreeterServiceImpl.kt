package no.proto.server

import io.grpc.stub.StreamObserver
import no.proto.generated.GreeterServiceGrpc
import no.proto.generated.HelloRequest
import no.proto.generated.HelloResponse

class GreeterServiceImpl : GreeterServiceGrpc.GreeterServiceImplBase() {

    //TODO skriv om til kotlin
    override fun sayHello(request: HelloRequest, responseObserver: StreamObserver<HelloResponse>) {
        val response = HelloResponse.newBuilder().setMessage("Hello ${request.name}").build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()

    }

}