package uk.co.itmms.demo.usecases.main

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Test
import uk.co.itmms.demo.BaseDomainTest
import uk.co.itmms.demo.failures.FailureMain
import uk.co.itmms.demo.repositories.IRepositorySystemInfo
import uk.co.itmms.demo.usecases.NoParams

@OptIn(ExperimentalCoroutinesApi::class)
class UseCaseMainInitTest : BaseDomainTest() {

    @MockK
    private lateinit var mockRepositorySystemInfo: IRepositorySystemInfo

    @InjectMockKs
    private lateinit var useCaseMainInit: UseCaseMainInit

    @Test
    fun `not enough memory`() = runBlocking {
        Dispatchers.setMain(Dispatchers.Unconfined)

        coEvery { mockRepositorySystemInfo.getSystemInfo() } returns IRepositorySystemInfo.SystemInfo(2124)

        useCaseMainInit.invoke(NoParams, this) { result ->
            result.fold({ failure ->
                assertEquals(FailureMain.NotEnoughMemory, failure)
            }){
                fail("Unexpected success")
            }
        }

        coVerify(exactly = 1) {
            mockRepositorySystemInfo.getSystemInfo()
        }
        confirmVerified(mockRepositorySystemInfo)
    }

    @Test
    fun `barely enough memory`() = runBlocking {
        Dispatchers.setMain(Dispatchers.Unconfined)

        coEvery { mockRepositorySystemInfo.getSystemInfo() } returns IRepositorySystemInfo.SystemInfo(5000)

        useCaseMainInit.invoke(NoParams,this) { result ->
            result.fold({ failure ->
                assertEquals(FailureMain.BarelyEnoughMemory, failure)
            }){
                fail("Unexpected success")
            }
        }

        coVerify(exactly = 1) {
            mockRepositorySystemInfo.getSystemInfo()
        }
        confirmVerified(mockRepositorySystemInfo)
    }

    @Test
    fun `more than enough memory`() = runBlocking {
        Dispatchers.setMain(Dispatchers.Unconfined)

        coEvery { mockRepositorySystemInfo.getSystemInfo() } returns IRepositorySystemInfo.SystemInfo(8000)

        useCaseMainInit.invoke(NoParams,this) { result ->
            assertTrue(result.isRight())
        }

        coVerify(exactly = 1) {
            mockRepositorySystemInfo.getSystemInfo()
        }
        confirmVerified(mockRepositorySystemInfo)
    }
}