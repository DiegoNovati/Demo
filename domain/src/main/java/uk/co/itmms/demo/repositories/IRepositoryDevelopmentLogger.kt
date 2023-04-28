package uk.co.itmms.demo.repositories

interface IRepositoryDevelopmentLogger {
    suspend fun setDevice(deviceId: String)
    suspend fun setProperty(name: String, value: String)

    suspend fun logIssue(simpleName: String, message: String)
    suspend fun logDebug(simpleName: String, message: String)
    suspend fun logInfo(simpleName: String, message: String)
    suspend fun logWarning(simpleName: String, message: String)
    suspend fun logError(simpleName: String, message: String)

    suspend fun send()
}