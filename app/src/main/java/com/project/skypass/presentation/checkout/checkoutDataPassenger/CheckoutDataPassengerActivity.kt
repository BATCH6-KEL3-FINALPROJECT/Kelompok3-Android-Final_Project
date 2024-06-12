package com.project.skypass.presentation.checkout.checkoutDataPassenger

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skypass.R
import com.project.skypass.data.model.sectionPassengerChckout
import com.project.skypass.databinding.ActivityCheckoutDataPassengerBinding
import com.project.skypass.databinding.ActivityCheckoutSeatBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.DataItem
import com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem.HeaderItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section

class CheckoutDataPassengerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDataPassengerBinding.inflate(layoutInflater)}
    private val adapter: GroupieAdapter by lazy {
        GroupieAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListeners()
        setData()
    }

    private fun setData() {
        binding.rvPassengerData.apply {
            layoutManager = LinearLayoutManager(this@CheckoutDataPassengerActivity)
            adapter = this@CheckoutDataPassengerActivity.adapter
        }

        val section = getListData().map {
            val section = Section()
            section.setHeader(HeaderItem(it.name) { data ->
                Toast.makeText(this, "Header Clicked : $data", Toast.LENGTH_SHORT).show()
            })
            val dataSection = it.data.map { data ->
                DataItem(data) { data ->
                    Toast.makeText(this, "Item Clicked : ${data[1]}", Toast.LENGTH_SHORT).show()
                }
            }
            section.addAll(dataSection)
            section
        }
        adapter.addAll(section)
    }

    private fun getListData(): List<sectionPassengerChckout> = listOf(
        sectionPassengerChckout("Data Passenger (name 1)", listOf("Nama orang 1")),
        sectionPassengerChckout("Data Passenger (name 2)", listOf("Nama Orang 2")),
        sectionPassengerChckout("Data Passenger (name 3)", listOf("Nama Orang 3")),
    )

    private fun setClickListeners() {
//        onclick binding
    }
    private fun observeResult(){
//        observe view model
    }
}