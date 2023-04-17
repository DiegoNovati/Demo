package uk.co.itmms.demo.repositories

interface IRepositoryDevelopmentAnalytics {
    fun logUseCase(simpleName: String, executionTime: Long)
}