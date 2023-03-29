package uk.co.itmms.demo.repositories

interface IRepositorySystemInfo {
    data class SystemInfo(
        val ram: Int,
    )

    suspend fun getSystemInfo(): SystemInfo
}