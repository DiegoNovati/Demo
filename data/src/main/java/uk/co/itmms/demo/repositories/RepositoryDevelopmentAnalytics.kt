package uk.co.itmms.demo.repositories

class RepositoryDevelopmentAnalytics : IRepositoryDevelopmentAnalytics {
    override fun logUseCase(simpleName: String, executionTime: Long) {
        println("---------- [Analytics] $simpleName took $executionTime ms ----------")
    }
}