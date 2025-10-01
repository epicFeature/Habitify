package org.eventhorizon.habitify

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.datastore.pref.PlutoDatastorePreferencesPlugin
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HabitifyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initPluto()
    }

    private fun initPluto() {
        Pluto.Installer(this)
            .addPlugin(PlutoNetworkPlugin())
            .addPlugin(PlutoLoggerPlugin())
            .addPlugin(PlutoDatastorePreferencesPlugin())
            .addPlugin(PlutoRoomsDatabasePlugin())
            .install()
    }
}