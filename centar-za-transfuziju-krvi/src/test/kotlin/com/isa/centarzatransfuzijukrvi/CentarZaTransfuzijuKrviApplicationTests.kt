package com.isa.centarzatransfuzijukrvi

import com.isa.centarzatransfuzijukrvi.model.Appointment
import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.service.AppointmentService
import com.isa.centarzatransfuzijukrvi.service.CenterService
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.Future


@RunWith(SpringRunner::class)
@SpringBootTest
class ScheduleAppointmentsTest {
	@Autowired
	private val appointmentService: AppointmentService? = null

	@Autowired
	private val registeredUserService: RegisteredUserService? = null

	@Before
	@Throws(Exception::class)
	fun setUp() {
		var app = appointmentService?.findAllEmptyAppointments()

	}

	@Test(expected = ObjectOptimisticLockingFailureException::class)
	@Throws(Throwable::class)
	fun testOptimisticLockingScenario() {
		val executor = Executors.newFixedThreadPool(2)
		val future1: Future<*> = executor.submit {
			println("Startovan Thread 1")
			var bda1: Optional<Appointment> = appointmentService?.appointmentRepository?.findById(5) as Optional<Appointment>
			var ru: RegisteredUser? = registeredUserService?.findByEmail("mail@mail.com")
			try {
				Thread.sleep(3000)
			} catch (e: InterruptedException) {
			}
			if (ru != null) {
				appointmentService.updateAppointment(5, ru)
			}
		}
		executor.submit {
			println("Startovan Thread 2")
			var ru: RegisteredUser? = registeredUserService?.findByEmail("mail@mail.com")
			if (ru != null) {
				appointmentService?.updateAppointment(5, ru)
			}
		}
		try {
			future1.get()
		} catch (e: ExecutionException) {
			println("Exception from thread " + e.cause!!.javaClass)
			throw e.cause!!
		} catch (e: InterruptedException) {
			e.printStackTrace()
		}
		executor.shutdown()
	}
}