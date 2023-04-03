package uk.co.itmms.demo.application

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import uk.co.itmms.demo.DataInterface

@HiltAndroidApp
class DemoApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        DataInterface.initDataLayer(this)
    }
}