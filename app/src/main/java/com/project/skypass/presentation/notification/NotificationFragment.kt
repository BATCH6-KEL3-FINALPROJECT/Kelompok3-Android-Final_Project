package com.project.skypass.presentation.notification

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.skypass.R
import com.project.skypass.databinding.FragmentHomeBinding
import com.project.skypass.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListener()
    }

    private fun clickListener() {
//        delete after sprint 1
        binding.layoutNotif1.cardItemNotification.setOnClickListener {
            binding.layoutNotif1.cardItemNotification.setBackgroundResource(R.color.abu)
        }
        binding.layoutNotif2.cardItemNotification.setOnClickListener {
            binding.layoutNotif2.cardItemNotification.setBackgroundResource(R.color.abu)
        }
        binding.layoutNotif3.cardItemNotification.setOnClickListener {
            binding.layoutNotif3.cardItemNotification.setBackgroundResource(R.color.abu)
        }
    }
}