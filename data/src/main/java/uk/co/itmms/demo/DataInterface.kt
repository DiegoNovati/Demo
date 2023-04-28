package uk.co.itmms.demo

import android.content.Context
import uk.co.itmms.demo.datasources.DataSourceDatabase
import uk.co.itmms.demo.datasources.DataSourceDevelopmentAnalyticsBugfender
import uk.co.itmms.demo.datasources.DataSourceDevelopmentAnalyticsConsole
import uk.co.itmms.demo.datasources.DataSourceDevelopmentLoggerBugfender
import uk.co.itmms.demo.datasources.DataSourceDevelopmentLoggerConsole
import uk.co.itmms.demo.datasources.DataSourcesSystemInfo
import uk.co.itmms.demo.datasources.IDataSourceDatabase
import uk.co.itmms.demo.datasources.IDataSourceDevelopmentAnalytics
import uk.co.itmms.demo.datasources.IDataSourceDevelopmentLogger
import uk.co.itmms.demo.datasources.IDataSourcesSystemInfo
import uk.co.itmms.demo.datasources.console.Console
import uk.co.itmms.demo.datasources.console.IConsole
import uk.co.itmms.demo.datasources.database.IDatabaseApp
import uk.co.itmms.demo.datasources.database.openDatabase
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.IRepositorySystemInfo
import uk.co.itmms.demo.repositories.IRepositoryTodo
import uk.co.itmms.demo.repositories.RepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.RepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.RepositorySystemInfo
import uk.co.itmms.demo.repositories.RepositoryTodo
import uk.co.itmms.demo.usecases.main.UseCaseMainInit
import uk.co.itmms.demo.usecases.main.UseCaseMainListTodo
import uk.co.itmms.demo.usecases.main.UseCaseMainSave

object DataInterface {

    private lateinit var applicationContext: Context
    private lateinit var databaseApp: IDatabaseApp

    fun initDataLayer(applicationContext: Context) {
        this.applicationContext = applicationContext

        databaseApp = openDatabase(applicationContext, "demo.db")
    }

    fun getUseCaseMainInit(): UseCaseMainInit =
        UseCaseMainInit(
            repositoryDevelopmentLogger = repositoryDevelopmentLogger,
            repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
            repositorySystemInfo = repositorySystemInfo,
        )

    fun getUseCaseMainListTodo(): UseCaseMainListTodo =
        UseCaseMainListTodo(
            repositoryDevelopmentLogger = repositoryDevelopmentLogger,
            repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
            repositoryTodo = repositoryTodo,
        )

    fun getUseCaseMainSave(): UseCaseMainSave =
        UseCaseMainSave(
            repositoryDevelopmentLogger = repositoryDevelopmentLogger,
            repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
            repositoryTodo = repositoryTodo,
        )

    private val repositorySystemInfo: IRepositorySystemInfo by lazy {
        RepositorySystemInfo(
            dataSourcesSystemInfo = dataSourcesSystemInfo,
        )
    }

    private val repositoryTodo: IRepositoryTodo by lazy {
        RepositoryTodo(
            dataSourceDatabase = dataSourceDatabase,
        )
    }

    private val repositoryDevelopmentLogger: IRepositoryDevelopmentLogger by lazy {
        RepositoryDevelopmentLogger(
            dataSourceDevelopmentLoggerList = listOf(
                dataSourceDevelopmentLoggerConsole,
                dataSourceDevelopmentLoggerBugfender,
            ),
        )
    }

    private val repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics by lazy {
        RepositoryDevelopmentAnalytics(
            dataSourceDevelopmentAnalyticsList = listOf(
                dataSourceDevelopmentAnalyticsConsole,
                dataSourceDevelopmentAnalyticsBugfender,
            ),
        )
    }


    private val dataSourceDevelopmentLoggerConsole: IDataSourceDevelopmentLogger by lazy {
        DataSourceDevelopmentLoggerConsole(
            console = console,
        )
    }

    private val dataSourceDevelopmentLoggerBugfender: IDataSourceDevelopmentLogger by lazy {
        DataSourceDevelopmentLoggerBugfender()
    }

    private val dataSourceDevelopmentAnalyticsConsole: IDataSourceDevelopmentAnalytics by lazy {
        DataSourceDevelopmentAnalyticsConsole(
            console = console,
        )
    }

    private val dataSourceDevelopmentAnalyticsBugfender: IDataSourceDevelopmentAnalytics by lazy {
        DataSourceDevelopmentAnalyticsBugfender()
    }

    private val dataSourcesSystemInfo: IDataSourcesSystemInfo by lazy {
        DataSourcesSystemInfo(
            context = applicationContext,
        )
    }

    private val dataSourceDatabase: IDataSourceDatabase by lazy {
        DataSourceDatabase(
            databaseApp = databaseApp,
        )
    }

    private val console: IConsole by lazy {
        Console()
    }
}