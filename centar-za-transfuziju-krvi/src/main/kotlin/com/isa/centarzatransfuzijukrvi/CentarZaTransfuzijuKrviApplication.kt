package com.isa.centarzatransfuzijukrvi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("com.isa.centarzatransfuzijukrvi")
@EnableJpaRepositories("com.isa.centarzatransfuzijukrvi")
class CentarZaTransfuzijuKrviApplication

fun main(args: Array<String>) {
	runApplication<CentarZaTransfuzijuKrviApplication>(*args)
}
