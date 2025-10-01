package org.eventhorizon.habitify.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.eventhorizon.habitify.data.utils.SystemDateUtil
import org.eventhorizon.habitify.domain.utils.DateUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {

    @Binds
    @Singleton
    abstract fun bindDateUtil(systemDateUtil: SystemDateUtil): DateUtil
}