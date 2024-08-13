package com.longdo.api3

import android.app.Application
import android.webkit.WebView
import com.longdo.map3.BuildConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupWebView()
    }

    private fun setupWebView() {
        WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
    }

}
