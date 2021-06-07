package br.com.example.controller

import br.com.demo.DemoGrpcServer.*
import br.com.demo.DemoGrpcServiceGrpc.*
import br.com.example.domain.DemoRequest
import br.com.example.repository.DemoRepository
import com.google.protobuf.Timestamp
import com.google.rpc.BadRequest
import io.grpc.Status
import io.grpc.StatusException
import io.grpc.stub.StreamObserver
import io.micronaut.validation.Validated
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Singleton
import javax.management.BadAttributeValueExpException
import javax.validation.Valid

@Singleton
class DemoController(val demoRepository: DemoRepository) : DemoGrpcServiceImplBase() {
    val log: Logger = LoggerFactory.getLogger(DemoController::class.java)

    override fun cadastrar(request: DemoGrpcRequest?, response: StreamObserver<DemoGrpcReply>?) {
        try {

        if (request?.name != "") {
            val demo = request?.let { DemoRequest(it).toDemo() }
            demoRepository.save(demo)
            log.info("Resultado: {}", demo)
        } else {
            throw BadAttributeValueExpException("Erro")
        }

        val date = LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDateTime()
        val resp = DemoGrpcReply.newBuilder()
            .setMessage("Demo user created")
            .setCriadoEm(Timestamp.newBuilder().setSeconds(date.second.toLong()).setNanos(date.nano).build())
            .build()

        response?.onNext(resp)
        response?.onCompleted()

    }
        catch (ex: Exception) {
            response?.onError(Status.INVALID_ARGUMENT
                .withDescription("Valor inválido")
                .withCause(Throwable(ex))
                .asRuntimeException())
            return
        }
    }
}