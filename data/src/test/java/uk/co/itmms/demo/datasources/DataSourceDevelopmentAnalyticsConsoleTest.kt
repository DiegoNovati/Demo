package uk.co.itmms.demo.datasources

import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test
import uk.co.itmms.demo.BaseDataTest
import uk.co.itmms.demo.datasources.DataSourceDevelopmentAnalyticsConsole.Companion.tag
import uk.co.itmms.demo.datasources.console.Console

class DataSourceDevelopmentAnalyticsConsoleTest : BaseDataTest() {

    @MockK
    private lateinit var mockConsole: Console

    @InjectMockKs
    private lateinit var dataSourceDevelopmentAnalyticsConsole: DataSourceDevelopmentAnalyticsConsole

    @Test
    fun `testing setDevice`() = runBlocking {
        val deviceId = "device ID"

        dataSourceDevelopmentAnalyticsConsole.setDevice(deviceId)

        coVerify(exactly = 1) {
            mockConsole.write(tag, "setDevice: deviceId = $deviceId")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing setProperty`() = runBlocking {
        val name = "name"
        val value = "value"

        dataSourceDevelopmentAnalyticsConsole.setProperty(name, value)

        coVerify(exactly = 1) {
            mockConsole.write(tag, "setProperty: name = $name, value = $value")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing logUseCase`() = runBlocking {
        val simpleName = "simple name"
        val executionTime = 123L

        dataSourceDevelopmentAnalyticsConsole.logUseCase(simpleName, executionTime)

        coVerify(exactly = 1) {
            mockConsole.write(tag, "logUseCase: simpleName = $simpleName, executionTime = $executionTime")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing logEvent`() = runBlocking {
        val eventName = "event name"
        val eventParamList = listOf<Pair<String, Any>>(
            "key1" to "value1",
            "key2" to "value2",
        )

        dataSourceDevelopmentAnalyticsConsole.logEvent(eventName, eventParamList)

        coVerify(exactly = 1) {
            mockConsole.write(tag, "logEvent: eventName = $eventName, eventParamList = $eventParamList")
        }
        confirmVerified(mockConsole)
    }
}