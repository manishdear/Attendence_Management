package com.unofficialcoder.attendence.data.local

import android.content.Context
import com.unofficialcoder.attendence.di.ApplicationContext
import com.unofficialcoder.attendence.di.DatabaseInfo


import javax.inject.Inject
import javax.inject.Singleton

/**
 * Dummy class to simulate the actual Database using Room or Realm etc
 */
@Singleton
class DatabaseService @Inject constructor(
    @ApplicationContext private val context: Context,
    @DatabaseInfo private val databaseName: String,
    @DatabaseInfo private val version: Int)// do the initialisation here
{

    val dummyData: String
        get() = "DATABASE_DUMMY_DATA"
}
