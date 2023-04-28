package uk.co.itmms.demo.repositories

import uk.co.itmms.demo.datasources.IDataSourceDevelopmentLogger

class RepositoryDevelopmentLogger(
    private val dataSourceDevelopmentLoggerList: List<IDataSourceDevelopmentLogger>,
) : IRepositoryDevelopmentLogger {
    override suspend fun setDevice(deviceId: String) =
        dataSourceDevelopmentLoggerList.forEach { it.setDevice(deviceId) }

    override suspend fun setProperty(name: String, value: String) =
        dataSourceDevelopmentLoggerList.forEach { it.setProperty(name, value) }

    override suspend fun logIssue(simpleName: String, message: String) =
        dataSourceDevelopmentLoggerList.forEach { it.logIssue(simpleName, message) }

    override suspend fun logDebug(simpleName: String, message: String) =
        dataSourceDevelopmentLoggerList.forEach { it.logDebug(simpleName, message) }

    override suspend fun logInfo(simpleName: String, message: String) =
        dataSourceDevelopmentLoggerList.forEach { it.logInfo(simpleName, message) }

    override suspend fun logWarning(simpleName: String, message: String) =
        dataSourceDevelopmentLoggerList.forEach { it.logWarning(simpleName, message) }

    override suspend fun logError(simpleName: String, message: String) =
        dataSourceDevelopmentLoggerList.forEach { it.logError(simpleName, message) }

    override suspend fun send() =
        dataSourceDevelopmentLoggerList.forEach { it.send() }
}