package com.project.skypass.presentation.profile.settingaccount

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.databinding.ActivitySettingsAccountBinding
import com.project.skypass.databinding.LayoutStateErrorBinding
import com.project.skypass.databinding.LayoutStateLoadingBinding
import com.project.skypass.databinding.LayoutStateSuccessBinding
import com.project.skypass.presentation.auth.verification.VerificationActivity
import com.project.skypass.utils.proceedWhen
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
        setSwitchListener()
        displayVerificationStatus()
    }

    private fun setOnClick(email: String) {
        binding.tvStatusAccount.setOnClickListener {
            val intent =
                Intent(this, VerificationActivity::class.java).apply {
                    putExtra("email", email)
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
            startActivity(intent)
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        settingsAccountViewModel.isUsingDarkMode.observe(this) { isUsingDarkMode ->
            applyUiMode(isUsingDarkMode)
        }
    }

    private fun displayVerificationStatus() {
        val userId = settingsAccountViewModel.getIdUser()
        settingsAccountViewModel.getUser(userId).observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    updateVerificationStatus(it.payload?.isVerified == true)
                    setOnClick(it.payload?.email ?: getString(R.string.email_error))
                },
                doOnLoading = {
                },
                doOnError = {
                },
            )
        }
    }

    private fun updateVerificationStatus(isVerified: Boolean) {
        val statusTextRes =
            if (isVerified) {
                getString(R.string.text_terverifikasi)
            } else {
                getString(R.string.text_belum_terverifikasi)
            }
        val statusTextColorRes =
            if (isVerified) {
                R.color.colorSuccess
            } else {
                R.color.colorFailed
            }
        binding.tvStatusAccount.apply {
            text = statusTextRes
            setTextColor(ContextCompat.getColor(context, statusTextColorRes))
        }
    }

    private fun applyUiMode(isUsingDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isUsingDarkMode) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            },
        )
        binding.switchTheme.isChecked = isUsingDarkMode
        updateThemeIcon(isUsingDarkMode)
    }

    private fun updateThemeIcon(isUsingDarkMode: Boolean) {
        val iconRes = if (isUsingDarkMode) R.drawable.ic_dark else R.drawable.ic_light
        binding.icTema.setImageResource(iconRes)
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
        builder.setTitle(getString(R.string.text_hapus_akun_profile))
        builder.setMessage(getString(R.string.text_dialog_hapus_akun))
        builder.setPositiveButton(getString(R.string.text_yes)) { dialog, _ ->
            deleteAccount()
            dialog.dismiss()
        }
        builder.setNegativeButton(getString(R.string.text_cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteOrderHistoryUser() {
        settingsAccountViewModel.deleteOrderHistoryUser().observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    Toast.makeText(
                        this,
                        getString(R.string.text_menghapus_riwayat_pemesanan),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnLoading = {
                    // Handle loading state if necessary
                },
                doOnError = { err ->
                    Toast.makeText(this, getString(R.string.text_error), Toast.LENGTH_SHORT).show()
                },
            )
        }
    }

    private fun deleteAccount() {
        //val userId = settingsAccountViewModel.getIdUser()
        settingsAccountViewModel.deleteUser(settingsAccountViewModel.getToken()).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    dialog?.dismiss()
                    // StyleableToast.makeText(this, "Hapus akun sukses", R.style.ToastSuccess).show()
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
                    // StyleableToast.makeText(this, "Hapus akun gagal", R.style.ToastError).show()
                    doError()
                },
            )
        }
    }

    private fun logout() {
        handleUnAuthorize()
    }

    private fun doLoading() {
        val dialogBinding = LayoutStateLoadingBinding.inflate(layoutInflater)
        dialog =
            Dialog(this).apply {
                setCancelable(true)
                setContentView(dialogBinding.root)
                show()
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
    }

    private fun doSuccess() {
        val dialogBinding = LayoutStateSuccessBinding.inflate(layoutInflater)
        dialog =
            Dialog(this).apply {
                setCancelable(true)
                setContentView(dialogBinding.root)
                show()
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
    }

    private fun doError() {
        val dialogBinding = LayoutStateErrorBinding.inflate(layoutInflater)
        dialog =
            Dialog(this).apply {
                setCancelable(true)
                setContentView(dialogBinding.root)
                show()
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
    }
}
