package com.project.skypass.presentation.checkout.checkoutmidtrans

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.R
import com.project.skypass.databinding.ActivityCheckoutMidtransBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutMidtransActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCheckoutMidtransBinding.inflate(layoutInflater) }
    private val viewModel: CheckoutMidtransViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        setupWebView()
    }

    private fun setClickListener(){
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val webView: WebView = findViewById(R.id.wv_midtrans)
        val url = intent.getStringExtra(EXTRA_MIDTRANS)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Toast.makeText(this@CheckoutMidtransActivity, "Web sukses dimuat", Toast.LENGTH_SHORT).show()
            }
        }
        webView.loadUrl(url!!)
    }

    companion object{
        const val EXTRA_MIDTRANS = "extra_midtrans"
    }
}