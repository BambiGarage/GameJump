package com.example.luckyball.jumperpack.ToWeb.Clients

import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.compose.ManagedActivityResultLauncher
import com.example.luckyball.jumperpack.ToWeb.ForZagruzkaFiles

class WewChromeClient(val afterPermissoin: ManagedActivityResultLauncher<String, Boolean>) : WebChromeClient() {

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {

        ForZagruzkaFiles.resultActivity = filePathCallback

        afterPermissoin.launch(android.Manifest.permission.CAMERA)


        return true
    }






}