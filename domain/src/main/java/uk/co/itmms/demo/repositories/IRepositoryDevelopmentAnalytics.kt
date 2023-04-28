package uk.co.itmms.demo.repositories

interface IRepositoryDevelopmentAnalytics {
    suspend fun setDevice(deviceId: String)
    suspend fun setProperty(name: String, value: String)

    suspend fun logUseCase(simpleName: String, executionTime: Long)
    suspend fun logEvent(eventName: String, eventParamList: List<Pair<String, Any>>)
}