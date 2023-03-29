package uk.co.itmms.demo.repositories

import uk.co.itmms.demo.datasources.IDataSourcesSystemInfo

class RepositorySystemInfo(
    private val dataSourcesSystemInfo: IDataSourcesSystemInfo,
) : IRepositorySystemInfo {

    override suspend fun getSystemInfo(): IRepositorySystemInfo.SystemInfo =
        IRepositorySystemInfo.SystemInfo(
            ram = dataSourcesSystemInfo.getSystemRamInMB(),
        )
}