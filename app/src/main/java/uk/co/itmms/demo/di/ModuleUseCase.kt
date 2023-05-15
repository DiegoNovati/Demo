package uk.co.itmms.demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.co.itmms.demo.DataInterface
import uk.co.itmms.demo.usecases.home.UseCaseHomeListNote
import uk.co.itmms.demo.usecases.main.UseCaseMainInit
import uk.co.itmms.demo.usecases.main.UseCaseMainList
import uk.co.itmms.demo.usecases.main.UseCaseMainListTodo
import uk.co.itmms.demo.usecases.main.UseCaseMainSave

@Module
@InstallIn(SingletonComponent::class)
object ModuleUseCase {

    @Provides
    fun provideUseCaseMainInit(): UseCaseMainInit =
        DataInterface.getUseCaseMainInit()

    @Provides
    fun provideUseCaseMainList(): UseCaseMainList =
        DataInterface.getUseCaseMainList()

    @Provides
    fun provideUseCaseMainListTodo(): UseCaseMainListTodo =
        DataInterface.getUseCaseMainListTodo()

    @Provides
    fun provideUseCaseMainSave(): UseCaseMainSave =
        DataInterface.getUseCaseMainSave()

    @Provides
    fun provideUseCaseHomeListNote(): UseCaseHomeListNote =
        DataInterface.getUseCaseHomeListNote()
}