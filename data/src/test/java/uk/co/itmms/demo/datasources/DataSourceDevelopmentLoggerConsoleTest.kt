package uk.co.itmms.demo.datasources

import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test
import uk.co.itmms.demo.BaseDataTest
import uk.co.itmms.demo.datasources.console.IConsole

class DataSourceDevelopmentLoggerConsoleTest : BaseDataTest() {

    @MockK
    private lateinit var mockConsole: IConsole

    @InjectMockKs
    private lateinit var dataSourceDevelopmentLoggerConsole: DataSourceDevelopmentLoggerConsole

    @Test
    fun `testing setDevice`() = runBlocking {
        val deviceId = "device ID"

        dataSourceDevelopmentLoggerConsole.setDevice(deviceId)

        coVerify(exactly = 1) {
            mockConsole.write(DataSourceDevelopmentLoggerConsole.tag, "setDevice: deviceId = $deviceId")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing setProperty`() = runBlocking {
        val name = "name"
        val value = "value"

        dataSourceDevelopmentLoggerConsole.setProperty(name, value)

        coVerify(exactly = 1) {
            mockConsole.write(DataSourceDevelopmentLoggerConsole.tag, "setProperty: name = $name, value = $value")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing logIssue`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        dataSourceDevelopmentLoggerConsole.logIssue(simpleName, message)

        coVerify(exactly = 1) {
            mockConsole.write(DataSourceDevelopmentLoggerConsole.tag, "logIssue: simpleName = $simpleName, message = $message")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing logDebug`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        dataSourceDevelopmentLoggerConsole.logDebug(simpleName, message)

        coVerify(exactly = 1) {
            mockConsole.write(DataSourceDevelopmentLoggerConsole.tag, "logDebug: simpleName = $simpleName, message = $message")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing logInfo`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        dataSourceDevelopmentLoggerConsole.logInfo(simpleName, message)

        coVerify(exactly = 1) {
            mockConsole.write(DataSourceDevelopmentLoggerConsole.tag, "logInfo: simpleName = $simpleName, message = $message")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing logWarning`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        dataSourceDevelopmentLoggerConsole.logWarning(simpleName, message)

        coVerify(exactly = 1) {
            mockConsole.write(DataSourceDevelopmentLoggerConsole.tag, "logWarning: simpleName = $simpleName, message = $message")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing logError`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        dataSourceDevelopmentLoggerConsole.logError(simpleName, message)

        coVerify(exactly = 1) {
            mockConsole.write(DataSourceDevelopmentLoggerConsole.tag, "logError: simpleName = $simpleName, message = $message")
        }
        confirmVerified(mockConsole)
    }

    @Test
    fun `testing send`() = runBlocking {
        dataSourceDevelopmentLoggerConsole.send()

        confirmVerified(mockConsole)
    }
}