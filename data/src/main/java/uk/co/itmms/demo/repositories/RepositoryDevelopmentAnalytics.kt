package uk.co.itmms.demo.repositories

import uk.co.itmms.demo.datasources.IDataSourceDevelopmentAnalytics

class RepositoryDevelopmentAnalytics(
    private val dataSourceDevelopmentAnalyticsList: List<IDataSourceDevelopmentAnalytics>,
) : IRepositoryDevelopmentAnalytics {
    override suspend fun setDevice(deviceId: String) =
        dataSourceDevelopmentAnalyticsList.forEach { it.setDevice(deviceId) }

    override suspend fun setProperty(name: String, value: String) =
        dataSourceDevelopmentAnalyticsList.forEach { it.setProperty(name, value) }

    override suspend fun logUseCase(simpleName: String, executionTime: Long) =
        dataSourceDevelopmentAnalyticsList.forEach { it.logUseCase(simpleName, executionTime) }

    override suspend fun logEvent(eventName: String, eventParamList: List<Pair<String, Any>>) =
        dataSourceDevelopmentAnalyticsList.forEach { it.logEvent(eventName, eventParamList) }
}