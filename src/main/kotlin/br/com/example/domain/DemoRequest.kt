package br.com.example.domain

import br.com.demo.DemoGrpcServer.DemoGrpcRequest
import io.micronaut.core.annotation.Introspected

@Introspected
class DemoRequest(request: DemoGrpcRequest) {
    val name: String = request.name
    val email: String = request.email
    val password: String = request.password
    val cargo: Cargo = Cargo.valueOf(request.cargo.toString())
    val endereco: List<Endereco> = request.enderecoList.map { Endereco(it.logradouro, it.cep, it.complemento) }

    fun toDemo(): Demo {
        return Demo(this.name, this.email, this.password, this.cargo, this.endereco)
    }

    override fun toString(): String {
        return "DemoRequest(name='$name', email='$email', password='$password', " +
                "cargo=$cargo, endereco=$endereco)"
    }
}