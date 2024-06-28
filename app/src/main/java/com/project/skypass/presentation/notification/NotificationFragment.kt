package com.project.skypass.presentation.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.FragmentNotificationBinding
import com.project.skypass.presentation.notification.adapter.NotificationAdapter
import com.project.skypass.presentation.notification.adapter.OnItemCLickedListener
import com.project.skypass.presentation.notification.detailNotification.DetailNotificationActivity
import com.project.skypass.utils.ApiErrorException
import com.project.skypass.utils.NoInternetException
import com.project.skypass.utils.UnauthorizedException
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    }

    private fun bindNotificationList() {
        viewModel.getNotification(viewModel.getToken()).observe(viewLifecycleOwner) { data ->
            data.proceedWhen(
                doOnSuccess = {
                    binding.layoutContentState.root.isVisible = false
                    notificationAdapter =
                        NotificationAdapter(
                            listener =
                            object : OnItemCLickedListener<Notification> {
                                override fun onItemClicked(item: Notification) {
                                    updateAndNavigateToDetail(item)
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
                }, doOnError = { error ->
                    binding.layoutContentState.textError.isVisible = true
                    Toast.makeText(requireContext(), "error broo", Toast.LENGTH_SHORT).show()
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.text =
                        getString(R.string.text_error_seat_checkout)
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                    if (error.exception is ApiErrorException) {
                        val errorMessage = error.exception.errorResponse
                        StyleableToast.makeText(requireContext(), errorMessage.message, R.style.ToastError).show()
                    } else if (error.exception is NoInternetException) {
                        StyleableToast.makeText(requireContext(), getString(R.string.no_internet_connection), R.style.ToastError).show()
                    } else if (error.exception is UnauthorizedException) {
                        val errorMessage = error.exception.errorUnauthorizedResponse
                        StyleableToast.makeText(requireContext(), errorMessage.message, R.style.ToastError).show()
                        lifecycleScope.launch {
                            delay(2000)
                            val activity = activity as? BaseActivity
                            activity?.handleUnAuthorize()
                        }
                    } else {
                        binding.layoutContentState.textError.text =
                            getString(R.string.unknown_error)
                    }
                }
            )
        }
    }

    private fun updateAndNavigateToDetail(item: Notification) {
        item.notificationId.let {
            viewModel.updateNotification(
                it
            ).observe(viewLifecycleOwner) { result ->
                result.proceedWhen(
                    doOnSuccess = {
                        navigateToDetail(item)
                    },
                    doOnError = {
                        Toast.makeText(
                            requireContext(),
                            "Failed to update notification",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
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