package no.proto.server

import no.proto.generated.GreeterServiceGrpcKt
import no.proto.generated.HelloRequest
import no.proto.generated.helloResponse

class GreeterServiceImpl : GreeterServiceGrpcKt.GreeterServiceCoroutineImplBase() {

    override suspend fun sayHello(request: HelloRequest) =
        helloResponse {
            message = "Hello ${request.name}"
        }


}


