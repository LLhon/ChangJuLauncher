package com.changju.launcher.common

import android.app.Application
import android.content.Context

/**
 *
 * Created by LLhon
 */
open class BaseApplication : Application() {

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}