package uk.co.itmms.demo.repositories

class RepositoryDevelopmentLogger : IRepositoryDevelopmentLogger {
    override fun logIssue(simpleName: String, createJson: String) {
        println("---------- [Logger] Issue: '$simpleName' with '$createJson'")
    }
}