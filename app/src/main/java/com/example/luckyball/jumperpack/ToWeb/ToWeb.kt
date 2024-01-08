package com.example.luckyball.jumperpack.ToWeb

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import com.example.luckyball.JumpActivity
import com.example.luckyball.jumperpack.ToWeb.Clients.WewChromeClient
import com.example.luckyball.jumperpack.ToWeb.Clients.WewViewClient
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

object SignlaCheck{
    var van = 0
}

@Composable
fun ToWeb(jumpActivity: JumpActivity) {

    LaunchedEffect(Unit){

        if (SignlaCheck.van == 0){
            val adId = withContext(Dispatchers.IO) {
                AdvertisingIdClient.getAdvertisingIdInfo(jumpActivity).id
            }
            OneSignal.initWithContext(jumpActivity, "fnefnoefniefnifneofin")
            OneSignal.login(adId.toString())
            SignlaCheck.van = 1
        }

    }
    
    val context = LocalContext.current
    val url = "https://fex.net/"


        val whenSaveFile =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == -1) {
                    if (it.data?.dataString == null) {
                        if (ForZagruzkaFiles.callback != null) {
                            ForZagruzkaFiles.resultActivity?.onReceiveValue(
                                arrayOf(
                                    ForZagruzkaFiles.callback!!
                                )
                            )
                        } else {
                            ForZagruzkaFiles.resultActivity?.onReceiveValue(null)
                        }
                    } else {
                        ForZagruzkaFiles.resultActivity?.onReceiveValue(arrayOf(Uri.parse(it.data!!.dataString!!)))
                    }
                } else {
                    ForZagruzkaFiles.resultActivity?.onReceiveValue(null)
                }
            }

        val afterPermissoin = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it == false) {
                ForZagruzkaFiles.resultActivity?.onReceiveValue(null)
            } else {
                val getContent = Intent(Intent.ACTION_GET_CONTENT)
                getContent.type = "*/*"
                getContent.addCategory(Intent.CATEGORY_OPENABLE)


                val fileTemp =
                    File.createTempFile("img", ".jpg", context.getExternalFilesDir("Pictures"))
                val uriParsed =  FileProvider.getUriForFile(context,
                    context.packageName,fileTemp)
                ForZagruzkaFiles.callback = uriParsed
                val camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                camIntent.putExtra("output", uriParsed)

                val putter = Intent(Intent.ACTION_CHOOSER)
                putter.putExtra(Intent.EXTRA_INTENT, getContent)
                putter.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(camIntent))
                whenSaveFile.launch(putter)

            }
        }


        val webView = remember {

            mutableStateOf(WebView(context))
        }
        BackHandler() {
            if (webView.value.canGoBack()) {

                webView.value.goBack()
            }
        }

        AndroidView(factory = {

            webView.value.apply {

                val instance: CookieManager = CookieManager.getInstance()
                instance.setAcceptCookie(true)
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
                settings.javaScriptEnabled = true

                settings.useWideViewPort = true
                settings.allowUniversalAccessFromFileURLs = true
                settings.loadWithOverviewMode = true
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.domStorageEnabled = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                settings.mixedContentMode = 0

                settings.databaseEnabled = true
                settings.allowFileAccessFromFileURLs = true

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WewViewClient()
                webChromeClient = WewChromeClient(afterPermissoin)
                loadUrl(url)
            }


            webView.value

        })



}


