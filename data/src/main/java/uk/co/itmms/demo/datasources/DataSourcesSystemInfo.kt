package uk.co.itmms.demo.datasources

import android.app.ActivityManager
import android.content.Context

interface IDataSourcesSystemInfo {
    suspend fun getSystemRamInMB(): Int
}

class DataSourcesSystemInfo(
    private val context: Context,
) : IDataSourcesSystemInfo {
    override suspend fun getSystemRamInMB(): Int {
        val actManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)
        return (memInfo.totalMem / (1024 * 1024)).toInt()
    }
}