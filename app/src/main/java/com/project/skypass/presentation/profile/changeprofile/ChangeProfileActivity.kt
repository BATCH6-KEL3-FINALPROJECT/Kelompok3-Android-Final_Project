package com.project.skypass.presentation.profile.changeprofile

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import coil.load
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.databinding.ActivityChangeProfileBinding
import com.project.skypass.databinding.LayoutStateErrorBinding
import com.project.skypass.databinding.LayoutStateLoadingBinding
import com.project.skypass.databinding.LayoutStateSuccessBinding
import com.project.skypass.utils.ApiErrorException
import com.project.skypass.utils.ImagePath
import com.project.skypass.utils.ImagePath.getRealPathFromURI
import com.project.skypass.utils.NoInternetException
import com.project.skypass.utils.UnauthorizedException
import com.project.skypass.utils.proceedWhen
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ChangeProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityChangeProfileBinding
    private val changeProfileViewModel: ChangeProfileViewModelExample by viewModel()
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null
    private var selectedImageFile: File? = null
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.ivProfile.setOnClickListener {
            openGallery()
        }

        displayProfileData()

        binding.btnEdit.setOnClickListener {
            val token = changeProfileViewModel.getToken()
            val userId = changeProfileViewModel.getUserId()
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etNumberPhone.text.toString()

            changeProfileViewModel.editUserData(token, userId, name, email, phoneNumber, selectedImageFile).observe(this) { result ->
                result.proceedWhen(
                    doOnSuccess = {
                        dialog?.dismiss()
                        lifecycleScope.launch {
                            delay(2000)
                            doSuccess()
                        }
                        finish()
                    },
                    doOnLoading = {
                        dialog?.dismiss()
                        doLoading()
                    },
                    doOnError = { error ->
                        dialog?.dismiss()
                        if (error.exception is ApiErrorException) {
                            val errorMessage = error.exception.errorResponse
                            StyleableToast.makeText(this, errorMessage.message, R.style.ToastError).show()
                        } else if (error.exception is NoInternetException) {
                            StyleableToast.makeText(this, getString(R.string.no_internet_connection), R.style.ToastError).show()
                        } else if (error.exception is UnauthorizedException) {
                            val errorMessage = error.exception.errorUnauthorizedResponse
                            StyleableToast.makeText(this, errorMessage.message, R.style.ToastError).show()
                            lifecycleScope.launch {
                                delay(2000)
                                handleUnAuthorize()
                            }
                        } else {
                            StyleableToast.makeText(this, getString(R.string.unknown_error), R.style.ToastError).show()
                        }
                    }
                )
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            binding.ivProfile.load(selectedImageUri)
            selectedImageUri?.let {
                selectedImageFile = File(getRealPathFromURI(this, it))
            }
        }
    }

    private fun displayProfileData() {
        val userId = changeProfileViewModel.getUserId()
        changeProfileViewModel.showDataUser(userId).observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { user ->
                        binding.ivProfile.load(user.photoUrl)
                        binding.etName.setText(user.name)
                        binding.etEmail.setText(user.email)
                        binding.etNumberPhone.setText(user.phoneNumber)
                    }
                },
                doOnLoading = {
                },
                doOnError = {
                }
            )
        }
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
