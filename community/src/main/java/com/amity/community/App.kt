package com.amity.community

import android.app.Application
import com.amity.socialcloud.sdk.AmityCoreClient

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val apiKey = getString(R.string.upstra_api_key)
        AmityCoreClient.setup(apiKey)
    }
}