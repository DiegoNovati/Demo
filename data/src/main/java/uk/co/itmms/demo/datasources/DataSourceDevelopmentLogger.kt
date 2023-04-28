package uk.co.itmms.demo.datasources

import uk.co.itmms.demo.datasources.console.IConsole

interface IDataSourceDevelopmentLogger {
    suspend fun setDevice(deviceId: String)
    suspend fun setProperty(name: String, value: String)

    suspend fun logIssue(simpleName: String, message: String)
    suspend fun logDebug(simpleName: String, message: String)
    suspend fun logInfo(simpleName: String, message: String)
    suspend fun logWarning(simpleName: String, message: String)
    suspend fun logError(simpleName: String, message: String)

    suspend fun send()
}

class DataSourceDevelopmentLoggerConsole(
    private val console: IConsole,
): IDataSourceDevelopmentLogger {

    companion object {
        const val tag = "logger"
    }

    override suspend fun setDevice(deviceId: String) =
        write("setDevice: deviceId = $deviceId")

    override suspend fun setProperty(name: String, value: String) =
        write("setProperty: name = $name, value = $value")

    override suspend fun logIssue(simpleName: String, message: String) =
        write("logIssue: simpleName = $simpleName, message = $message")

    override suspend fun logDebug(simpleName: String, message: String) =
        write("logDebug: simpleName = $simpleName, message = $message")

    override suspend fun logInfo(simpleName: String, message: String) =
        write("logInfo: simpleName = $simpleName, message = $message")

    override suspend fun logWarning(simpleName: String, message: String) =
        write("logWarning: simpleName = $simpleName, message = $message")

    override suspend fun logError(simpleName: String, message: String) =
        write("logError: simpleName = $simpleName, message = $message")

    override suspend fun send() {}

    private fun write(message: String) {
        console.write(tag, message)
    }
}

class DataSourceDevelopmentLoggerBugfender: IDataSourceDevelopmentLogger {
    override suspend fun setDevice(deviceId: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun setProperty(name: String, value: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun logIssue(simpleName: String, message: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun logDebug(simpleName: String, message: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun logInfo(simpleName: String, message: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun logWarning(simpleName: String, message: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun logError(simpleName: String, message: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun send() {
        //TODO("Not yet implemented")
    }
}