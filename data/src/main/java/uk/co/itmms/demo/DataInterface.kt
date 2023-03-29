package uk.co.itmms.demo

import android.content.Context
import uk.co.itmms.demo.datasources.DataSourcesSystemInfo
import uk.co.itmms.demo.datasources.IDataSourcesSystemInfo
import uk.co.itmms.demo.repositories.IRepositorySystemInfo
import uk.co.itmms.demo.repositories.RepositorySystemInfo
import uk.co.itmms.demo.usecases.main.UseCaseMainInit

object DataInterface {

    private lateinit var applicationContext: Context

    fun initDataLayer(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

    fun getUseCaseMainInit(): UseCaseMainInit =
        UseCaseMainInit(
            repositorySystemInfo = repositorySystemInfo,
        )

    private val repositorySystemInfo: IRepositorySystemInfo by lazy {
        RepositorySystemInfo(
            dataSourcesSystemInfo = dataSourcesSystemInfo,
        )
    }

    private val dataSourcesSystemInfo: IDataSourcesSystemInfo by lazy {
        DataSourcesSystemInfo(applicationContext)
    }
}