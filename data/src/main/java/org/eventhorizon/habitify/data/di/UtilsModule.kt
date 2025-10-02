package org.eventhorizon.habitify.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.eventhorizon.habitify.data.utils.SystemDateUtil
import org.eventhorizon.habitify.domain.utils.DateUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideDateUtil(systemDateUtil: SystemDateUtil): DateUtil {
        return systemDateUtil
    }
}