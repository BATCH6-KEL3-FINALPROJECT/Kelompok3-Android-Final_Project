package com.project.skypass.presentation.history

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.databinding.FragmentHistoryBinding
import com.project.skypass.presentation.history.adapter.HistoryMonthItem
import com.project.skypass.presentation.history.adapter.HistoryTicketItem
import com.project.skypass.presentation.history.filter.date.CalendarHistoryFragment
import com.project.skypass.presentation.history.filter.search.SearchHistoryFragment
import com.project.skypass.utils.ApiErrorException
import com.project.skypass.utils.NoInternetException
import com.project.skypass.utils.UnauthorizedException
import com.project.skypass.utils.convertDateMouth
import com.project.skypass.utils.proceedWhen
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HistoryFragment : Fragment(), OnSearchItemSelectedListener {
    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModel()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
        setData()
        observeResult()
    }

    private fun setData() {
        binding.rvTicketHistoryOrder.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HistoryFragment.adapter
        }
    }

    private fun observeResult() {
        viewModel.getAllHistory(viewModel.getToken()).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = { result ->
                    binding.shimmerViewContainer.isVisible = false
                    binding.shimmerViewContainer.stopShimmer()
                    binding.layoutContentState.root.isVisible = false
                    result.payload?.let { data ->
                        val items = mutableListOf<BindableItem<*>>()

                        val groupedData = data.groupBy { convertDateMouth(it.bookingDate) }

                        groupedData.forEach { (mount, data) ->
                            items.add(HistoryMonthItem(mount))
                            data.forEach { list ->
                                items.add(HistoryTicketItem(list))
                            }
                        }
                        adapter.update(items)
                    }
                },
                doOnEmpty = {
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.isVisible = true
                    binding.layoutContentState.textError.text =
                        getString(R.string.text_empty_seat_class)
                    val linkLoad =
                        "https://github.com/riansyah251641/food_app_asset/blob/main/banner/empty_history.png?raw=true"
                    binding.layoutContentState.ivRiwayatKosong.load(linkLoad) {
                        crossfade(true)
                        error(R.drawable.bg_no_internet)
                    }
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                },
                doOnLoading = {
                    binding.layoutContentState.root.isVisible = false
                    binding.shimmerViewContainer.isVisible = true
                    binding.shimmerViewContainer.startShimmer()
                },
                doOnError = { error ->
                    binding.layoutContentState.root.isVisible = true
                    binding.layoutContentState.textError.isVisible = true
                    binding.layoutContentState.root.isVisible = true
                    val linkLoad =
                        "https://github.com/riansyah251641/food_app_asset/blob/main/banner/empty_history.png?raw=true"
                    binding.layoutContentState.ivRiwayatKosong.load(linkLoad) {
                        crossfade(true)
                        error(R.drawable.bg_no_internet)
                    }
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                    if (error.exception is NoInternetException) {
                        StyleableToast.makeText(
                            requireContext(),
                            getString(R.string.no_internet_connection),
                            R.style.ToastError,
                        ).show()
                    } else if (error.exception is UnauthorizedException) {
                        val errorMessage = error.exception.errorUnauthorizedResponse
                        StyleableToast.makeText(
                            requireContext(),
                            errorMessage.message,
                            R.style.ToastError,
                        ).show()
                        lifecycleScope.launch {
                            delay(2000)
                            val activity = activity as? BaseActivity
                            activity?.handleUnAuthorize()
                        }
                    } else {
                        binding.layoutContentState.textError.text =
                            getString(R.string.unknown_error)
                    }
                    binding.layoutContentState.pbLoadingEmptyState.isVisible = false
                },
            )
        }
    }

    private fun clickListener() {
        binding.ivSearchHistory.setOnClickListener {
            val searchFragment = SearchHistoryFragment()
            searchFragment.setTargetFragment(this, 0)
            searchFragment.show(parentFragmentManager, searchFragment.tag)
        }
        binding.llFilterContainer.setOnClickListener {
            val calendarFragment = CalendarHistoryFragment()
            calendarFragment.setTargetFragment(this, 101)
            calendarFragment.show(parentFragmentManager, calendarFragment.tag)
        }
    }

    override fun onSearchItemSelected(searchQuery: String) {
        viewModel.getBookingHistory(viewModel.getToken(), searchQuery, null, null)
            ?.observe(viewLifecycleOwner) { result ->
                result.proceedWhen(
                    doOnSuccess = { response ->
                        binding.layoutContentState.root.isVisible = false
                        response.payload?.let { data ->
                            val items = mutableListOf<BindableItem<*>>()

                            val groupedData = data.groupBy { convertDateMouth(it.bookingDate) }

                            groupedData.forEach { (month, historyList) ->
                                items.add(HistoryMonthItem(month))
                                historyList.forEach { history ->
                                    items.add(HistoryTicketItem(history))
                                }
                            }
                            adapter.update(items)
                        }
                    },
                    doOnError = { error ->
                        if (error.exception is ApiErrorException) {
                            val errorMessage = error.exception.errorResponse
                            StyleableToast.makeText(
                                requireContext(),
                                errorMessage.message,
                                R.style.ToastError,
                            ).show()
                        } else if (error.exception is NoInternetException) {
                            StyleableToast.makeText(
                                requireContext(),
                                getString(R.string.no_internet_connection),
                                R.style.ToastError,
                            ).show()
                        } else if (error.exception is UnauthorizedException) {
                            val errorMessage = error.exception.errorUnauthorizedResponse
                            StyleableToast.makeText(
                                requireContext(),
                                errorMessage.message,
                                R.style.ToastError,
                            ).show()
                            lifecycleScope.launch {
                                delay(2000)
                                val activity = activity as? BaseActivity
                                activity?.handleUnAuthorize()
                            }
                        } else {
                            binding.layoutContentState.textError.text =
                                getString(R.string.unknown_error)
                        }
                    },
                )
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                0 -> {
                    val searchQuery = data?.getStringExtra("searchQuery")
                    searchQuery?.let { onSearchItemSelected(it) }
                }
                101 -> {
                    val departureDateString = data?.getStringExtra("selectedDateDeparture")
                    val returnDateString = data?.getStringExtra("selectedDateReturn")
                    if (departureDateString != null && returnDateString != null) {
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val departureDate = LocalDate.parse(departureDateString, formatter)
                        val returnDate = LocalDate.parse(returnDateString, formatter)
                        onDateSelected(departureDate.toString(), returnDate.toString())
                    }
                }
            }
        }
    }

    private fun onDateSelected(startDate: String, endDate: String) {
        viewModel.getBookingHistory(viewModel.getToken(), null, startDate, endDate)?.observe(viewLifecycleOwner) { result ->
            result.proceedWhen(
                doOnSuccess = { response ->
                    binding.layoutContentState.root.isVisible = false
                    response.payload?.let { data ->
                        val items = mutableListOf<BindableItem<*>>()

                        val groupedData = data.groupBy { convertDateMouth(it.bookingDate) }

                        groupedData.forEach { (month, historyList) ->
                            items.add(HistoryMonthItem(month))
                            historyList.forEach { history ->
                                items.add(HistoryTicketItem(history))
                            }
                        }
                        adapter.update(items)
                    }
                },
                doOnError = {
                    Toast.makeText(requireContext(), "Data Booking not found", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

interface OnSearchItemSelectedListener {
    fun onSearchItemSelected(searchQuery: String)
}
