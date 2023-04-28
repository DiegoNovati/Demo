package uk.co.itmms.demo.datasources

import uk.co.itmms.demo.datasources.console.IConsole

interface IDataSourceDevelopmentAnalytics {
    suspend fun setDevice(deviceId: String)
    suspend fun setProperty(name: String, value: String)

    suspend fun logUseCase(simpleName: String, executionTime: Long)
    suspend fun logEvent(eventName: String, eventParamList: List<Pair<String, Any>>)
}

class DataSourceDevelopmentAnalyticsConsole(
    private val console: IConsole,
) : IDataSourceDevelopmentAnalytics {

    companion object {
        const val tag = "analytics"
    }

    override suspend fun setDevice(deviceId: String) =
        write("setDevice: deviceId = $deviceId")

    override suspend fun setProperty(name: String, value: String) =
        write("setProperty: name = $name, value = $value")

    override suspend fun logUseCase(simpleName: String, executionTime: Long) =
        write("logUseCase: simpleName = $simpleName, executionTime = $executionTime")

    override suspend fun logEvent(eventName: String, eventParamList: List<Pair<String, Any>>) =
        write("logEvent: eventName = $eventName, eventParamList = $eventParamList")

    private fun write(message: String) {
        console.write(tag, message)
    }
}

class DataSourceDevelopmentAnalyticsBugfender : IDataSourceDevelopmentAnalytics {
    override suspend fun setDevice(deviceId: String) =
        write("setDevice: deviceId = $deviceId")

    override suspend fun setProperty(name: String, value: String) =
        write("setProperty: name = $name, value = $value")

    override suspend fun logUseCase(simpleName: String, executionTime: Long) =
        write("logUseCase: simpleName = $simpleName, executionTime = $executionTime")

    override suspend fun logEvent(eventName: String, eventParamList: List<Pair<String, Any>>) =
        write("logEvent: eventName = $eventName, eventParamList = $eventParamList")

    private fun write(message: String) {
        println("[bugfender] $message")
    }
}
