package br.com.example.domain

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
class Demo() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String = ""
    var email: String = ""
    var password: String = ""
    @Enumerated(EnumType.STRING)
    var cargo: Cargo? = null
    @OneToMany
    @Cascade(CascadeType.PERSIST)
    var endereco: List<Endereco>? = null

    constructor(name: String,
                email: String,
                password: String,
                cargo: Cargo,
                endereco: List<Endereco>): this() {
        this.name = name
        this.cargo = cargo
        this.endereco = endereco
        this.email = email
        this.password = password
    }

    override fun toString(): String {
        return "Demo(name='$name', email='$email', password='$password', " +
                "cargo=$cargo, endereco=$endereco)"
    }
}