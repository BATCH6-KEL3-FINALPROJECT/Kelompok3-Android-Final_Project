package com.project.skypass.presentation.notification.detailNotification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.skypass.R
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.ActivityCheckoutDataOrdersBinding
import com.project.skypass.databinding.ActivityDetailNotificationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNotificationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailNotificationBinding.inflate(layoutInflater) }

    private val viewModel: DetailNotificationViewModel by viewModel()

    companion object {
        const val EXTRAS_ITEM = "EXTRAS_ITEM"
        fun startActivity(context: Context, notificationData: Notification) {
            val intent = Intent(context, DetailNotificationActivity::class.java)
            intent.putExtra(EXTRAS_ITEM, notificationData)
            context.startActivity((intent))
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        setClickListeners()
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<Notification>(EXTRAS_ITEM)?. let {
            setProfileData(it)
        }
    }

    private fun setProfileData(item: Notification) {
        binding.tvTitleNotification.text = item.title
        binding.tvDateNotification.text = item.date
        binding.tvTypeNotification.text = item.category
        binding.tvDetailItemNotification.text = item.body

    }
    private fun setClickListeners() {
        binding.ivBtnBack.setOnClickListener {
            finish()
        }
    }
    private fun observeResult(){
//        observe view model
    }


}