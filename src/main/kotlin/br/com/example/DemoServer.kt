package br.com.example

import br.com.demo.DemoGrpcServer.*
import br.com.demo.DemoGrpcServiceGrpc.*
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

fun main() {
    val demoServer = ServerBuilder.forPort(50051)
        .addService(DemoService())
        .build()
    demoServer.start()
    println("Server started in the port ${demoServer.port}")
    demoServer.awaitTermination()
}

class DemoService: DemoGrpcServiceImplBase() {
    override fun cadastrar(request: DemoGrpcRequest?, responseObserver: StreamObserver<DemoGrpcReply>?) {
        val response = DemoGrpcReply.newBuilder()
            .setMessage("Hello World ${request?.name}")
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}