package com.isa.centarzatransfuzijukrvi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
class CentarZaTransfuzijuKrviApplication

fun main(args: Array<String>) {
	runApplication<CentarZaTransfuzijuKrviApplication>(*args)
}
