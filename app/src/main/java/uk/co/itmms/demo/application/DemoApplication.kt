package uk.co.itmms.demo.application

import android.app.Application
import uk.co.itmms.demo.DataInterface

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DataInterface.initDataLayer(this)
    }
}