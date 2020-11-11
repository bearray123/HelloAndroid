/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.example.browser

import android.app.Activity
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.webkit.*

/**
 * Created by xionglei01@baidu.com on 2020/11/10.
 */
class CommonWebActivity : Activity() {

    private val mWebView : WebView by lazy { findViewById<WebView>(R.id.webview_id) }

    private var mWebViewClient : WebViewClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_layout)

        mWebView.settings.run {
            javaScriptEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE // 不使用缓存，只从网络获取数据.
            allowFileAccess = true
//            loadsImagesAutomatically = true
            domStorageEnabled = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            allowUniversalAccessFromFileURLs = true
        }
        mWebViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request.toString())
//                return super.shouldOverrideUrlLoading(view, request)
                return true
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
//                super.onReceivedSslError(view, handler, error)
                handler?.proceed()
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
//                super.onReceivedError(view, request, error)
            }
        }
        mWebView.webViewClient = mWebViewClient

        mWebView.loadUrl("https://m3ws.kugou.com/kgsong/3uybj19.html?albumid=0&frombaidu")

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}