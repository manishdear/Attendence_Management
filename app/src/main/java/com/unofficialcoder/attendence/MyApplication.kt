package com.unofficialcoder.attendence

import android.app.Application
import com.unofficialcoder.attendence.data.local.DatabaseService
import com.unofficialcoder.attendence.data.remote.NetworkService
import com.unofficialcoder.attendence.di.component.ApplicationComponent
import com.unofficialcoder.attendence.di.component.DaggerApplicationComponent
import com.unofficialcoder.attendence.di.module.ApplicationModule

import javax.inject.Inject

class   MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var databaseService: DatabaseService

    lateinit var mainActivity: MainActivity

    override fun onCreate() {
        super.onCreate()
        getDependencies()
        mainActivity = MainActivity()
    }

    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }
}