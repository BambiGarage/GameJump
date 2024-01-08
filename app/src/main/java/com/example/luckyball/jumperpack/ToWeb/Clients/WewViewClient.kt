package com.example.luckyball.jumperpack.ToWeb.Clients

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient


class WewViewClient() : WebViewClient() {


    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        val url = request?.url.toString()
        println(url)
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return false
        } else {
            try {
                val intent: Intent
                if (url.startsWith("intent:")) {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    if (intent.action == "com.google.firebase.dynamiclinks.VIEW_DYNAMIC_LINK"){
                        intent.extras?.getString("browser_fallback_url")
                            ?.let { view?.loadUrl(it) }
                        return true
                    }
                } else {
                    intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                }
                view!!.context.startActivity(intent)

            } catch (_: Exception) {
            }
            return true
        }

    }


}


