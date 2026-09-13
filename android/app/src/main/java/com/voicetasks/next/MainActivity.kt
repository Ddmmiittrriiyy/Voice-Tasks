package com.voicetasks.next

import android.Manifest
import android.app.*
import android.os.*
import android.content.pm.PackageManager
import android.webkit.*
import android.widget.Toast

class MainActivity:Activity(){
    private lateinit var web:WebView
    override fun onCreate(b:Bundle?){
        super.onCreate(b)
        web=WebView(this)
        setContentView(web)
        web.settings.javaScriptEnabled=true
        web.settings.domStorageEnabled=true
        web.settings.mediaPlaybackRequiresUserGesture=false
        web.webViewClient=WebViewClient()
        web.addJavascriptInterface(AndroidBridge(this),"AndroidBridge")
        if(Build.VERSION.SDK_INT>=33 && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED)
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS),100)
        web.loadUrl("https://ddmmiittrriiyy.github.io/Voice-Tasks/")
    }
    override fun onBackPressed(){ if(web.canGoBack()) web.goBack() else super.onBackPressed() }
}
