package br.com.example.repository

import br.com.example.domain.Demo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface DemoRepository: JpaRepository<Demo, Long> {
}