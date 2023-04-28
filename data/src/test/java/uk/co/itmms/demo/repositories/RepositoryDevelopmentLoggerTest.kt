package uk.co.itmms.demo.repositories

import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import uk.co.itmms.demo.BaseDataTest
import uk.co.itmms.demo.datasources.IDataSourceDevelopmentLogger

class RepositoryDevelopmentLoggerTest : BaseDataTest() {

    @MockK
    private lateinit var dataSourceDevelopmentLoggerOne: IDataSourceDevelopmentLogger

    @MockK
    private lateinit var dataSourceDevelopmentLoggerTwo: IDataSourceDevelopmentLogger

    private lateinit var repositoryDevelopmentLogger: RepositoryDevelopmentLogger

    @Before
    fun setUp() {
        repositoryDevelopmentLogger = RepositoryDevelopmentLogger(
            listOf(
                dataSourceDevelopmentLoggerOne,
                dataSourceDevelopmentLoggerTwo,
            )
        )
    }

    @Test
    fun `testing setDevice`() = runBlocking {
        val deviceId = "device ID"

        repositoryDevelopmentLogger.setDevice(deviceId)

        coVerify(exactly = 1) {
            dataSourceDevelopmentLoggerOne.setDevice(deviceId)
            dataSourceDevelopmentLoggerTwo.setDevice(deviceId)
        }
        confirmVerified(dataSourceDevelopmentLoggerOne, dataSourceDevelopmentLoggerTwo)
    }

    @Test
    fun `testing setProperty`() = runBlocking {
        val name = "name"
        val value = "value"

        repositoryDevelopmentLogger.setProperty(name, value)

        coVerify(exactly = 1) {
            dataSourceDevelopmentLoggerOne.setProperty(name, value)
            dataSourceDevelopmentLoggerTwo.setProperty(name, value)
        }
        confirmVerified(dataSourceDevelopmentLoggerOne, dataSourceDevelopmentLoggerTwo)
    }

    @Test
    fun `testing logIssue`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        repositoryDevelopmentLogger.logIssue(simpleName, message)

        coVerify(exactly = 1) {
            dataSourceDevelopmentLoggerOne.logIssue(simpleName, message)
            dataSourceDevelopmentLoggerTwo.logIssue(simpleName, message)
        }
        confirmVerified(dataSourceDevelopmentLoggerOne, dataSourceDevelopmentLoggerTwo)
    }

    @Test
    fun `testing logDebug`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        repositoryDevelopmentLogger.logDebug(simpleName, message)

        coVerify(exactly = 1) {
            dataSourceDevelopmentLoggerOne.logDebug(simpleName, message)
            dataSourceDevelopmentLoggerTwo.logDebug(simpleName, message)
        }
        confirmVerified(dataSourceDevelopmentLoggerOne, dataSourceDevelopmentLoggerTwo)
    }

    @Test
    fun `testing logInfo`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        repositoryDevelopmentLogger.logInfo(simpleName, message)

        coVerify(exactly = 1) {
            dataSourceDevelopmentLoggerOne.logInfo(simpleName, message)
            dataSourceDevelopmentLoggerTwo.logInfo(simpleName, message)
        }
        confirmVerified(dataSourceDevelopmentLoggerOne, dataSourceDevelopmentLoggerTwo)
    }

    @Test
    fun `testing logWarning`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        repositoryDevelopmentLogger.logWarning(simpleName, message)

        coVerify(exactly = 1) {
            dataSourceDevelopmentLoggerOne.logWarning(simpleName, message)
            dataSourceDevelopmentLoggerTwo.logWarning(simpleName, message)
        }
        confirmVerified(dataSourceDevelopmentLoggerOne, dataSourceDevelopmentLoggerTwo)
    }

    @Test
    fun `testing logError`() = runBlocking {
        val simpleName = "simple name"
        val message = "message"

        repositoryDevelopmentLogger.logError(simpleName, message)

        coVerify(exactly = 1) {
            dataSourceDevelopmentLoggerOne.logError(simpleName, message)
            dataSourceDevelopmentLoggerTwo.logError(simpleName, message)
        }
        confirmVerified(dataSourceDevelopmentLoggerOne, dataSourceDevelopmentLoggerTwo)
    }

    @Test
    fun `testing send`() = runBlocking {
        repositoryDevelopmentLogger.send()

        coVerify(exactly = 1) {
            dataSourceDevelopmentLoggerOne.send()
            dataSourceDevelopmentLoggerTwo.send()
        }
        confirmVerified(dataSourceDevelopmentLoggerOne, dataSourceDevelopmentLoggerTwo)
    }
}