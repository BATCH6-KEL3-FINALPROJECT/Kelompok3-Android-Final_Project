package com.project.skypass.presentation.notification

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.FragmentHomeBinding
import com.project.skypass.databinding.FragmentNotificationBinding
import com.project.skypass.presentation.notification.adapter.NotificationAdapter
import com.project.skypass.presentation.notification.adapter.OnItemCLickedListener
import com.project.skypass.presentation.notification.detailNotification.DetailNotificationActivity
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    /*private val dataSourceNotification: DataSourceNotification by lazy { DataSourceNotificationImpl() }*/

    private val viewModel: NotificationViewModel by viewModel()

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


    private fun bindNotificationList() {

        viewModel.getNotification(viewModel.getToken()).observe(viewLifecycleOwner) {data ->
            data.proceedWhen(
                doOnSuccess = {
                    binding.layoutContentState.root.isVisible = false
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
                    it.payload?.let { data ->
                    notificationAdapter?.submitData(data)
                        }
                },
                doOnEmpty = {
                    binding.layoutContentState.textError.isVisible = true
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.text =
                        getString(R.string.text_empty_notification)
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                },
                doOnLoading = {
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.isVisible = false
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = true
                }, doOnError = {
                    binding.layoutContentState.textError.isVisible = true
                    Toast.makeText(requireContext(), "error broo", Toast.LENGTH_SHORT).show()
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.text =
                        getString(R.string.text_error_seat_checkout)
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                }
            )
        }
    }

    private fun navigateToDetail(item: Notification) {
        DetailNotificationActivity.startActivity(
            requireContext(), Notification(
                item.id,
                item.notificationId,
                item.userId,
                item.flightId,
                item.bookingId,
                item.promotionId,
                item.notificationType,
                item.message,
                item.isRead,
                item.createdAt,
                item.updatedAt
            )
        )
    }
}