package com.project.skypass.presentation.profile.settingaccount

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.databinding.ActivitySettingsAccountBinding
import com.project.skypass.databinding.LayoutStateErrorBinding
import com.project.skypass.databinding.LayoutStateLoadingBinding
import com.project.skypass.databinding.LayoutStateSuccessBinding
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsAccountActivity : BaseActivity() {

    private val binding: ActivitySettingsAccountBinding by lazy {
        ActivitySettingsAccountBinding.inflate(layoutInflater)
    }

    private val settingsAccountViewModel: SettingsAccountViewModel by viewModel()
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        settingsAccountViewModel.isUsingDarkMode.observe(this) { isUsingDarkMode ->
            applyUiMode(isUsingDarkMode)
        }

        setSwitchListener()
    }

    private fun applyUiMode(isUsingDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isUsingDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        binding.switchTheme.isChecked = isUsingDarkMode
    }

    private fun setSwitchListener() {
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            settingsAccountViewModel.setUsingDarkMode(isChecked)
        }
        binding.clDeleteAccount.setOnClickListener {
            dialogDeleteAccount()
        }
    }

    private fun dialogDeleteAccount() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hapus Akun")
        builder.setMessage("Apakah kamu yakin ingin hapus akun?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            deleteAccount()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteOrderHistoryUser() {
        settingsAccountViewModel.deleteOrderHistoryUser().observe(this){
            it.proceedWhen(doOnSuccess = {
                Toast.makeText(this, "Menghapus Riwayat Pemesanan", Toast.LENGTH_SHORT).show()
            }, doOnLoading = {

            }, doOnError = { err ->
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun deleteAccount() {
        val userId = settingsAccountViewModel.getIdUser()
        settingsAccountViewModel.deleteUser(userId).observe(this){
            it.proceedWhen(
                doOnSuccess = {
                    dialog?.dismiss()
                    //StyleableToast.makeText(this, "Hapus akun sukses", R.style.ToastSuccess).show()
                    doSuccess()
                    deleteOrderHistoryUser()
                    lifecycleScope.launch {
                        delay(2000)
                        logout()
                    }
                },
                doOnLoading = {
                    dialog?.dismiss()
                    doLoading()
                },
                doOnError = {
                    dialog?.dismiss()
                    //StyleableToast.makeText(this, "Hapus akun gagal", R.style.ToastError).show()
                    doError()
                }
            )
        }
    }

    private fun logout() {
        handleUnAuthorize()
    }

    private fun doLoading(){
        val dialogBinding = LayoutStateLoadingBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun doSuccess(){
        val dialogBinding = LayoutStateSuccessBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun doError(){
        val dialogBinding = LayoutStateErrorBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}
