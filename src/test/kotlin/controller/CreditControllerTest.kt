package controller

import com.app.credit.application.repositories.CreditRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

class CreditControllerTest {

    @BeforeEach
    fun setup() = CreditRepository.deleteAll()

    @AfterEach
    fun tearDown() = CreditRepository.deleteAll()
}