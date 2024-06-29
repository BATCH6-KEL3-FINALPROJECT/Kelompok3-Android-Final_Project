package com.project.skypass.presentation.notification.detailNotification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.skypass.R
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.ActivityCheckoutDataOrdersBinding
import com.project.skypass.databinding.ActivityDetailNotificationBinding
import com.project.skypass.utils.convertDateNotification
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailNotificationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailNotificationBinding.inflate(layoutInflater) }

    private val viewModel: DetailNotificationViewModel by viewModel{
        parametersOf(intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showData(viewModel.notificationData)
        setClickBackListener()
    }

    private fun showData(data: Notification?) {
        data?.let {
            binding.tvDateNotification.text = convertDateNotification(it.createdAt)
            binding.tvTitleNotification.text = it.notificationType
            binding.tvDetailItemNotification.text = it.message
        }
    }

    private fun setClickBackListener(){
        binding.ivBtnBack.setOnClickListener {
            finish()
        }
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    companion object {
        const val EXTRA_NOTIFICATION = "EXTRA_NOTIFICATION"
    }

}