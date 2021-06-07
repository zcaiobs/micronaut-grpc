package br.com.example

import br.com.demo.DemoGrpcServer.*
import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val demo = DemoGrpcRequest.newBuilder()
        .setName("Demo")
        .setEmail("demo@email.com")
        .setPassword("12345")
        .build()

    println(demo)
    demo.writeTo(FileOutputStream("demo.bin"))
    val read = DemoGrpcRequest.newBuilder().mergeFrom(FileInputStream("demo.bin"))
    read.email = "demo@demo.com"
    println(read)
}