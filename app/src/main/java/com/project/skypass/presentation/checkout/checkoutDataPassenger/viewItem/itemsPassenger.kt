package com.project.skypass.presentation.checkout.checkoutDataPassenger.viewItem

import android.app.DatePickerDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.data.model.PassengersData
import com.project.skypass.databinding.ItemCheckoutDataPassengerBinding
import com.project.skypass.databinding.ItemCheckoutHeaderPassengerBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.xwray.groupie.viewbinding.BindableItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DataItem(private var data: PassengersData, private val onItemClick: (item: PassengersData) -> Unit) :
    BindableItem<ItemCheckoutDataPassengerBinding>() {

    override fun bind(viewBinding: ItemCheckoutDataPassengerBinding, position: Int) {

        setupTitleDropdown(viewBinding)
        viewBinding.etDateBorn.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                setDateBordCalender(viewBinding)
            }
        }

        viewBinding.etLastDateKTP.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    setDateKTPCalender(viewBinding)
                }
            }

        addFamilyName(viewBinding)
        viewBinding.btnSubmit.setOnClickListener {
            val passengersData = PassengersData(
                title = viewBinding.etDropdownMenu.text.toString(),
                firstName = viewBinding.etName.text.toString(),
                lastName = viewBinding.etFamilyName.text.toString(),
                nationality = viewBinding.etCitizenship.text.toString(),
                passportNo = viewBinding.etKtpPassport.text.toString(),
                issuingCountry = viewBinding.etLastDateKTP.text.toString(),
                dateOfBirth = viewBinding.etDateBorn.text.toString(),
                email = CheckoutDataPassengerActivity.DataHolder.emailOrder.toString(),
                phoneNumber = viewBinding.etNoPhone.text.toString(),
                validUntil = viewBinding.etLastDateKTP.text.toString(),
                passengerType = null
            )
            if (passengersData.isValid()) {
                viewBinding.btnSubmit.isVisible = false
                viewBinding.etDropdownMenu.isEnabled = false
                viewBinding.etName.isEnabled = false
                viewBinding.etFamilyName.isEnabled = false
                viewBinding.etCitizenship.isEnabled = false
                viewBinding.etKtpPassport.isEnabled = false
                viewBinding.etLastDateKTP.isEnabled = false
                viewBinding.etDateBorn.isEnabled = false
                viewBinding.etNoPhone.isEnabled = false
                viewBinding.etLastDateKTP.isEnabled = false

                onItemClick.invoke(passengersData)
            } else {
                Toast.makeText(
                    viewBinding.root.context,
                    "Terdapat Data Yang Kosong",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
    private fun PassengersData.isValid(): Boolean {
        return title.isNotEmpty() &&
                firstName.isNotEmpty() &&
                nationality.isNotEmpty() &&
                passportNo.isNotEmpty() &&
                issuingCountry.isNotEmpty() &&
                dateOfBirth.isNotEmpty() &&
                email.isNotEmpty() &&
                phoneNumber.isNotEmpty() &&
                validUntil.isNotEmpty()
    }

    private fun addFamilyName(binding: ItemCheckoutDataPassengerBinding) {
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            binding.inputFamilyName.isVisible = isChecked
        }
    }
    private fun setupTitleDropdown(binding: ItemCheckoutDataPassengerBinding) {
        val titles = listOf("Mr", "Ms")
        val adapterDropdown = ArrayAdapter(binding.root.context, R.layout.item_checkout_passengers_title, titles)
        binding.etDropdownMenu.setAdapter(adapterDropdown)
    }


    private fun setDateBordCalender(viewBinding: ItemCheckoutDataPassengerBinding) {
        val calendar = Calendar.getInstance()
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
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        dateBornCalendar.show()
    }
    private fun setDateKTPCalender(viewBinding: ItemCheckoutDataPassengerBinding) {
        val calendar = Calendar.getInstance()
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
                    viewBinding.etLastDateKTP.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
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