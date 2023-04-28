package uk.co.itmms.demo.repositories

import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import uk.co.itmms.demo.BaseDataTest
import uk.co.itmms.demo.datasources.IDataSourceDevelopmentAnalytics

class RepositoryDevelopmentAnalyticsTest : BaseDataTest() {

    @MockK
    private lateinit var dataSourceDevelopmentAnalyticsOne: IDataSourceDevelopmentAnalytics

    @MockK
    private lateinit var dataSourceDevelopmentAnalyticsTwo: IDataSourceDevelopmentAnalytics

    private lateinit var repositoryDevelopmentAnalytics: RepositoryDevelopmentAnalytics

    @Before
    fun setUp() {
        repositoryDevelopmentAnalytics = RepositoryDevelopmentAnalytics(
            listOf(
                dataSourceDevelopmentAnalyticsOne,
                dataSourceDevelopmentAnalyticsTwo,
            )
        )
    }

    @Test
    fun `testing setDevice`() = runBlocking {
        val deviceId = "Device ID"

        repositoryDevelopmentAnalytics.setDevice(deviceId)

        coVerify(exactly = 1) {
            dataSourceDevelopmentAnalyticsOne.setDevice(deviceId)
            dataSourceDevelopmentAnalyticsTwo.setDevice(deviceId)
        }
        confirmVerified(dataSourceDevelopmentAnalyticsOne, dataSourceDevelopmentAnalyticsTwo)
    }

    @Test
    fun `testing setProperty`() = runBlocking {
        val name = "Name"
        val value = "Value"

        repositoryDevelopmentAnalytics.setProperty(name, value)

        coVerify(exactly = 1) {
            dataSourceDevelopmentAnalyticsOne.setProperty(name, value)
            dataSourceDevelopmentAnalyticsTwo.setProperty(name, value)
        }
        confirmVerified(dataSourceDevelopmentAnalyticsOne, dataSourceDevelopmentAnalyticsTwo)
    }

    @Test
    fun `testing logUseCase`() = runBlocking {
        val simpleName = "Simple Name"
        val executionTime = 123L

        repositoryDevelopmentAnalytics.logUseCase(simpleName, executionTime)

        coVerify(exactly = 1) {
            dataSourceDevelopmentAnalyticsOne.logUseCase(simpleName, executionTime)
            dataSourceDevelopmentAnalyticsTwo.logUseCase(simpleName, executionTime)
        }
        confirmVerified(dataSourceDevelopmentAnalyticsOne, dataSourceDevelopmentAnalyticsTwo)
    }

    @Test
    fun `testing logEvent`() = runBlocking {
        val eventName = "Event Name"
        val eventParamList = listOf<Pair<String, Any>>()

        repositoryDevelopmentAnalytics.logEvent(eventName, eventParamList)

        coVerify(exactly = 1) {
            dataSourceDevelopmentAnalyticsOne.logEvent(eventName, eventParamList)
            dataSourceDevelopmentAnalyticsTwo.logEvent(eventName, eventParamList)
        }
        confirmVerified(dataSourceDevelopmentAnalyticsOne, dataSourceDevelopmentAnalyticsTwo)
    }
}