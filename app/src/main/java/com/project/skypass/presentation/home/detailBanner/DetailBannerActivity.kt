package com.project.skypass.presentation.home.detailBanner

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import coil.size.Scale
import com.project.skypass.R
import com.project.skypass.data.model.BannerHome
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityDetailBannerBinding
import com.project.skypass.databinding.ActivityFlightDetailBinding
import com.project.skypass.presentation.flight.detail.FlightDetailActivity
import com.project.skypass.presentation.flight.detail.FlightDetailActivity.Companion.EXTRA_FLIGHT

class DetailBannerActivity : AppCompatActivity() {
    private val binding: ActivityDetailBannerBinding by lazy {
        ActivityDetailBannerBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        setOnClick()
    }
    private fun setOnClick(){
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<BannerHome>(EXTRA_BANNER)?.let {
            setProfileData(it)
        }
    }

    private fun setProfileData(item: BannerHome){
        binding.ivBanner.load(item.imageUrl){
            crossfade(true)
            scale(Scale.FIT)
        }
        binding.tvTitleBanner.text = item.city
        binding.tvTitleDetailBanner.text = item.title
        binding.tvDetailBannerDesk.text = item.longDescription

        binding.btnExplore.setOnClickListener {
            val linkInfo = item.linkInfo

            if (linkInfo.isNotBlank()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkInfo))
                startActivity(intent)
            }
        }
    }


    companion object {
        const val EXTRA_BANNER = "EXTRA_BANNER"
        fun startActivity(
            context: Context,
            item: BannerHome,
        ) {
            val intent = Intent(context, DetailBannerActivity::class.java)
            intent.putExtra(EXTRA_BANNER, item)
            context.startActivity((intent))
        }
    }
}