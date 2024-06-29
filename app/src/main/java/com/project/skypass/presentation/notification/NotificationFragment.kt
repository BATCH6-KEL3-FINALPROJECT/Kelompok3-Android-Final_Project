package com.project.skypass.presentation.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.FragmentNotificationBinding
import com.project.skypass.presentation.customview.OnItemAdapterClickedListener
import com.project.skypass.presentation.notification.adapter.NotificationAdapter
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
    private val viewModel: NotificationViewModel by viewModel()
    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        //observeResult()
    }

    override fun onResume() {
        super.onResume()
        observeResult()
    }

    private fun observeResult() {
        viewModel.getNotification(viewModel.getToken()).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = { result ->
                    binding.rvNotification.isVisible = true
                    binding.layoutContentState.root.isVisible = false
                    result.payload?.let { data ->
                        setBindNotification(data)
                    }
                },
                doOnLoading = {
                    binding.rvNotification.isVisible = false
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.isVisible = false
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = true
                },
                doOnError = { error ->
                    binding.rvNotification.isVisible = false
                    binding.layoutContentState.textError.isVisible = true
                    binding.layoutContentState.root.isVisible = true
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

    private fun setupAdapter() {
        notificationAdapter = NotificationAdapter(object : OnItemAdapterClickedListener<Notification> {
            override fun onClicked(item: Notification) {
                navigateToDetailNotification(item)
            }
        })
        binding.rvNotification.apply {
            adapter = this@NotificationFragment.notificationAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigateToDetailNotification(item: Notification) {
        viewModel.updateNotification(item.notificationId).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = { result ->
                    val navController = findNavController()
                    val bundleNotification = bundleOf(DetailNotificationActivity.EXTRA_NOTIFICATION to item)
                    navController.navigate(R.id.action_menu_tab_notification_to_detailNotificationActivity, bundleNotification)
                },
                doOnLoading = {
                    // Handle loading state if necessary
                },
                doOnError = { err ->
                    // Handle error state if necessary
                }
            )
        }
    }

    private fun setBindNotification(item: List<Notification>) {
        notificationAdapter.submitData(item)
        /*val sortedList = item.sortedByDescending { it.createdAt }
        notificationAdapter.submitData(sortedList)*/
    }

}