package com.project.skypass.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.skypass.R
import com.project.skypass.databinding.FragmentHistoryBinding
import com.project.skypass.databinding.FragmentHomeBinding
import com.project.skypass.presentation.history.filter.date.CalendarHistoryFragment
import com.project.skypass.presentation.history.filter.search.SearchHistoryFragment
import dev.jahidhasanco.seatbookview.SeatBookView
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener() {
        binding.ivSearchHistory.setOnClickListener {
            val searchFragment = SearchHistoryFragment()
            searchFragment.show(childFragmentManager, searchFragment.tag)

        }
        binding.llFilterContainer.setOnClickListener {
            val calendarFragment = CalendarHistoryFragment()
            calendarFragment.show(childFragmentManager, calendarFragment.tag)
        }
    }

}