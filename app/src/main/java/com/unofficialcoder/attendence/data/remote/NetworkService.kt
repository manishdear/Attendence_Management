package com.unofficialcoder.attendence.data.remote

import android.content.Context
import com.unofficialcoder.attendence.di.ApplicationContext
import com.unofficialcoder.attendence.di.NetworkInfo


import javax.inject.Inject
import javax.inject.Singleton

/**
 * Dummy class to simulate the actual NetworkService using Retrofit or OkHttp etc
 */
@Singleton
class NetworkService @Inject constructor(
    @ApplicationContext private val context: Context,
    @NetworkInfo private val apiKey: String)// do the initialisation here
{

    val dummyData: String
        get() = "NETWORK_DUMMY_DATA"
}
