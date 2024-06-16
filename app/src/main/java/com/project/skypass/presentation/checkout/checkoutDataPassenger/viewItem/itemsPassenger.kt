package com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem

import android.app.DatePickerDialog
import android.view.View
import android.content.Intent
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutDataPassengerBinding
import com.project.skypass.databinding.ItemCheckoutDataPassengerBinding
import com.project.skypass.databinding.ItemCheckoutHeaderPassengerBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatActivity
import com.xwray.groupie.viewbinding.BindableItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.content.Context
import com.project.skypass.data.model.OrderPassangers


class DataItem(private var data: String, private val onItemClick: (item: String) -> Unit) :
    BindableItem<ItemCheckoutDataPassengerBinding>() {

    override fun bind(viewBinding: ItemCheckoutDataPassengerBinding, position: Int) {
        val dataName = viewBinding.etName.text.toString()
        val dataPhone = viewBinding.etNoPhone
        val dataCitizen = viewBinding.etCitizenship.text.toString()
        val dataFamilyName = viewBinding.etFamilyName
        val dataCountryKTP = viewBinding.etCountryKTP
        val dataDateBorn = viewBinding.etDateBorn
        data = viewBinding.etName.text.toString()
        viewBinding.etDateBorn.setOnClickListener{setDateBordCalender(viewBinding)}
        addFamilyName(viewBinding)
        viewBinding.root.setOnClickListener { onItemClick.invoke(data) }


    }

    private fun addFamilyName(binding: ItemCheckoutDataPassengerBinding) {
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            binding.inputFamilyName.isVisible = isChecked
        }
    }

    private fun setDateBordCalender(viewBinding: ItemCheckoutDataPassengerBinding) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dateBornCalendar =
            DatePickerDialog(
                viewBinding.root.context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    calendar.apply {
                        set(Calendar.YEAR, selectedYear)
                        set(Calendar.MONTH, selectedMonth)
                        set(Calendar.DAY_OF_MONTH, selectedDay)
                    }
                    val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale("id", "ID")).format(calendar.time)
                    viewBinding.etDateBorn.setText(formattedDate)
                },
                day,
                month,
                year,
            )
        dateBornCalendar.show()
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