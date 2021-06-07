package br.com.example.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Endereco() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var logradouro: String = ""
    var cep: String = ""
    var complemento: String = ""

    constructor(logradouro: String, cep: String, complemento: String) : this() {
        this.logradouro = logradouro
        this.cep = cep
        this.complemento = complemento
    }

    override fun toString(): String {
        return "Endereco(logradouro='$logradouro', cep='$cep', complemento='$complemento')"
    }
}