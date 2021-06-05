package br.com.example

import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

fun main() {
    val demoServer = ServerBuilder.forPort(50051)
        .addService(DemoService())
        .build()

    demoServer.start()
    demoServer.awaitTermination()
}

class DemoService: MicronautGrpcServiceGrpc.MicronautGrpcServiceImplBase() {
    override fun send(request: MicronautGrpcRequest?, responseObserver: StreamObserver<MicronautGrpcReply>?) {
        val response = MicronautGrpcReply.newBuilder()
            .setMessage("Hello World Demo")
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}