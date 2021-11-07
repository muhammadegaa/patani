package com.fluid.smartcrops

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import java.lang.Exception

class Dashboard : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var back_btn: ImageView

    private var video_url = ""
    private var html = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnBack = findViewById(R.id.btn_back) as ImageView

        btnBack.setOnClickListener {
            finish()
        }

        val webview = findViewById(R.id.wv_dashboard) as WebView
        webview.webChromeClient = WebChromeClient()
        webview.settings.loadsImagesAutomatically = true
        webview.settings.javaScriptEnabled = true
        webview.settings.allowFileAccess = true
        webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.settings.pluginState = WebSettings.PluginState.ON
        webview.settings.mediaPlaybackRequiresUserGesture = true
        webview.settings.domStorageEnabled = true
        webview.settings.setAppCacheMaxSize((1024 * 8).toLong())
        webview.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webview.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webview.requestFocus(View.FOCUS_DOWN)
        webview.settings.setAppCacheEnabled(true)

        webview.loadUrl("https://app.powerbi.com/reportEmbed?reportId=fdaad131-c8e5-4e58-b894-901e976aeb17&autoAuth=true&ctid=fcf67057-50c9-4ad4-98f3-ffca64add9e9&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly93YWJpLXdlc3QtdXMtYi1wcmltYXJ5LXJlZGlyZWN0LmFuYWx5c2lzLndpbmRvd3MubmV0LyJ9\n")

//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//
//        back_btn = findViewById(R.id.btn_back)
//        back_btn.setOnClickListener{
//            webView.loadData("", "text/html", "UTF-8")
//            finish()
//        }
//
//        webView = findViewById(R.id.wv_dashboard);
//        progressBar = findViewById(R.id.progress_circular);
//
////        if (video_url.equals("", ignoreCase = true)) {
////            finish()
////            return
////        }
//
//        var ws: WebSettings = webView.settings
//        ws.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
//        ws.pluginState = WebSettings.PluginState.ON
//        ws.javaScriptEnabled = true
//        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
//        webView.reload()
//
////        if (networkUtil.isConnectingToInternet(Act_VideoPlayer.this)) {
////            html = getHTML(video_url);
////        } else {
////            html = "" + getResources().getString(R.string.The_internet_connection_appears_to_be_offline);
////            CustomToast.animRedTextMethod(Act_VideoPlayer.this, getResources().getString(R.string.The_internet_connection_appears_to_be_offline));
////        }
//
//        webView.loadData(html, "text/html", "UTF-8");
//
//        var webViewClient = WebClientClass(progressBar)
//        webView.setWebViewClient(webViewClient)
//        var webChromeClient = WebChromeClient()
//        webView.webChromeClient = webChromeClient
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        try {
//            webView!!.loadData("", "text/html", "UTF-8")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        try {
//            webView!!.loadData("", "text/html", "UTF-8")
//            finish()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    inner class WebClientClass internal constructor(progressBar: ProgressBar?) :
//        WebViewClient() {
//        var ProgressBar: ProgressBar? = null
//        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//            super.onPageStarted(view, url, favicon)
//            ProgressBar!!.visibility = View.VISIBLE
//        }
//
//        override fun onPageFinished(view: WebView, url: String) {
//            super.onPageFinished(view, url)
//            ProgressBar!!.visibility = View.GONE
//        }
//
//        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
////            LogShowHide.LogShowHideMethod("webview-click :", "" + url)
//            view.loadUrl(getHTML(url))
//            return true
//        }
//
//        init {
//            ProgressBar = progressBar
//        }
//    }
//
//    fun getHTML(videoId: String): String {
//        val html =
//            """<iframe width="1140" height="541.25" src="https://app.powerbi.com/reportEmbed?reportId=fdaad131-c8e5-4e58-b894-901e976aeb17&autoAuth=true&ctid=fcf67057-50c9-4ad4-98f3-ffca64add9e9&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly93YWJpLXdlc3QtdXMtYi1wcmltYXJ5LXJlZGlyZWN0LmFuYWx5c2lzLndpbmRvd3MubmV0LyJ9" frameborder="0" allowFullScreen="true"></iframe>
//"""
////        LogShowHide.LogShowHideMethod("video-id from html url= ", "" + html)
//        return html
//    }

}