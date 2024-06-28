package com.project.skypass.presentation.checkout.checkoutmidtrans

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.R
import com.project.skypass.databinding.ActivityCheckoutMidtransBinding
import com.project.skypass.presentation.auth.login.LoginActivity
import com.project.skypass.presentation.main.MainActivity
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
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToMain()
            }
        })
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val webView: WebView = findViewById(R.id.wv_midtrans)
        val url = intent.getStringExtra(EXTRA_MIDTRANS)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Toast.makeText(this@CheckoutMidtransActivity,
                    getString(R.string.web_success), Toast.LENGTH_SHORT).show()
            }
        }
        webView.loadUrl(url!!)
    }

    companion object{
        const val EXTRA_MIDTRANS = "extra_midtrans"
    }
}