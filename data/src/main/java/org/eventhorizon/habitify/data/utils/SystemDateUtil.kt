package org.eventhorizon.habitify.data.utils

import android.os.Build
import androidx.annotation.RequiresApi
import org.eventhorizon.habitify.domain.utils.DateUtil
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SystemDateUtil @Inject constructor() : DateUtil { //todo зачем тут inject constructor
    @RequiresApi(Build.VERSION_CODES.O)
    override fun today(): LocalDate {
        return LocalDate.now()
    }
}