package com.NEO_OREZ.persianpodcastplus.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.NEO_OREZ.persianpodcastplus.databinding.FragmentWebViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WebViewFragment : Fragment() {
    lateinit var bindingWebView : FragmentWebViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            withContext(Dispatchers.Main){
                bindingWebView.webViewID.webViewClient =  WebViewClient()
                bindingWebView.webViewID.loadUrl("https://www.bing.com")
                bindingWebView.webViewID.settings.javaScriptEnabled = true

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingWebView = FragmentWebViewBinding.inflate(inflater, container, false)
        return bindingWebView.root
    }
}