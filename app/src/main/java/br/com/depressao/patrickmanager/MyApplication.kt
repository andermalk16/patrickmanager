package br.com.depressao.patrickmanager

import android.app.Application
import br.com.depressao.patrickmanager.di.components.ApplicationComponent
import br.com.depressao.patrickmanager.di.components.DaggerApplicationComponent
import br.com.depressao.patrickmanager.di.modules.ApplicationModule
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by Anderson Silva on 28/03/17.
 */
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)

        Components.applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    object Components {
        lateinit var applicationComponent: ApplicationComponent
    }
}