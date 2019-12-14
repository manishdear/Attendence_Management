package com.unofficialcoder.attendence.di.component

import android.content.Context
import com.unofficialcoder.attendence.MyApplication
import com.unofficialcoder.attendence.data.local.DatabaseService
import com.unofficialcoder.attendence.data.remote.NetworkService
import com.unofficialcoder.attendence.di.ApplicationContext
import com.unofficialcoder.attendence.di.module.ApplicationModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getCompositeDisposable(): CompositeDisposable
}
