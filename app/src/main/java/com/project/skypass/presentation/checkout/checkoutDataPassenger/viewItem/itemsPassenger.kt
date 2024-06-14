package com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem

import android.view.View
import com.project.skypass.R
import com.project.skypass.databinding.ActivityCheckoutDataPassengerBinding
import com.project.skypass.databinding.ItemCheckoutDataPassengerBinding
import com.project.skypass.databinding.ItemCheckoutHeaderPassengerBinding
import com.xwray.groupie.viewbinding.BindableItem



class DataItem(private val data: String, private val onItemClick: (item: String) -> Unit) :
    BindableItem<ItemCheckoutDataPassengerBinding>() {


    override fun bind(viewBinding: ItemCheckoutDataPassengerBinding, position: Int) {
        val dataEmail = viewBinding.etEmail
        val dataPhone = viewBinding.etNoPhone
        val dataCitizen = viewBinding.etCitizenship
        val dataFamilyName = viewBinding.etFamilyName
        val dataCountryKTP = viewBinding.etCountryKTP
        val dataDateBorn = viewBinding.etDateBorn
        viewBinding.root.setOnClickListener { onItemClick.invoke("$dataEmail $dataCitizen") }
    }

    override fun getLayout(): Int = R.layout.item_checkout_data_passenger

    override fun initializeViewBinding(view: View): ItemCheckoutDataPassengerBinding =
        ItemCheckoutDataPassengerBinding.bind(view)
}
class HeaderItem(private val title: String, private val onHeaderClick: (item: String) -> Unit) :
    BindableItem<ItemCheckoutHeaderPassengerBinding>() {
    override fun bind(viewBinding: ItemCheckoutHeaderPassengerBinding, position: Int) {
        viewBinding.tvTitlePassenger.text = title
        viewBinding.root.setOnClickListener { onHeaderClick.invoke(title) }
    }

    override fun getLayout(): Int = R.layout.item_checkout_header_passenger

    override fun initializeViewBinding(view: View): ItemCheckoutHeaderPassengerBinding =
        ItemCheckoutHeaderPassengerBinding.bind(view)
}