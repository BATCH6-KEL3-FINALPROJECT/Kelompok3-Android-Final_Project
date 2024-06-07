package com.project.skypass.presentation.notification

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.skypass.R
import com.project.skypass.data.datasource.notification.DataSourceNotification
import com.project.skypass.data.datasource.notification.DataSourceNotificationImpl
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.FragmentHomeBinding
import com.project.skypass.databinding.FragmentNotificationBinding
import com.project.skypass.presentation.notification.adapter.NotificationAdapter
import com.project.skypass.presentation.notification.adapter.OnItemCLickedListener
import com.project.skypass.presentation.notification.detailNotification.DetailNotificationActivity

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private val dataSourceNotification: DataSourceNotification by lazy { DataSourceNotificationImpl() }

    private var notificationAdapter: NotificationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // bindItemNotification()
        bindNotificationList()
        clickListener()
    }

    private fun clickListener() {

    }

    private fun bindItemNotification(){
        binding.rvNotification.apply {
            adapter = this@NotificationFragment.notificationAdapter
        }
        notificationAdapter?.submitData(dataSourceNotification.getNotificationItem())
    }

    private fun bindNotificationList() {
        notificationAdapter =
            NotificationAdapter(
                listener =
                object : OnItemCLickedListener<Notification> {
                    override fun onItemClicked(item: Notification) {
                        navigateToDetail(item)
                    }
                },
            )
        binding.rvNotification.adapter = this@NotificationFragment.notificationAdapter
        notificationAdapter?.submitData(dataSourceNotification.getNotificationItem())
    }


    private fun navigateToDetail(item: Notification) {
        DetailNotificationActivity.startActivity(
            requireContext(), Notification(
                item.id,
                item.title,
                item.body,
                item.category,
                item.date,
                item.status,


            )
        )
    }
}