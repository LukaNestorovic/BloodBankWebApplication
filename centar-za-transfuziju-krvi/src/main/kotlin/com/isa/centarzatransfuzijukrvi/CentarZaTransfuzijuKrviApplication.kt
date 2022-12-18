package com.isa.centarzatransfuzijukrvi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class CentarZaTransfuzijuKrviApplication

fun main(args: Array<String>) {
	runApplication<CentarZaTransfuzijuKrviApplication>(*args)
}
