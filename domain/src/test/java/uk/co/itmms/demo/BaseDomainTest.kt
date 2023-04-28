package uk.co.itmms.demo

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger

abstract class BaseDomainTest {

    @MockK
    protected lateinit var mockRepositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics

    @MockK
    protected lateinit var mockRepositoryDevelopmentLogger: IRepositoryDevelopmentLogger

    @Before
    fun initMocks() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}