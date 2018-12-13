package com.example.kalmar17.pololop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val url = "https://habr.com"
        webViewMap.webViewClient = WebViewClient()
        webViewMap.settings.javaScriptEnabled = true
        webViewMap.loadUrl(url)

    }
}

