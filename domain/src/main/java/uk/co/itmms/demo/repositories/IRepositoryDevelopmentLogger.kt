package uk.co.itmms.demo.repositories

interface IRepositoryDevelopmentLogger {
    fun logIssue(simpleName: String, createJson: String)
}