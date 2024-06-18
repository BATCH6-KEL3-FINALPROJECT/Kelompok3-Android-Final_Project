package com.project.skypass.presentation.profile.changeprofile

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.project.skypass.databinding.ActivityChangeProfileBinding
import com.project.skypass.utils.proceedWhen
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangeProfileBinding
    private val changeProfileViewModel: ChangeProfileViewModelExample by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        displayProfileData()

        binding.btnEdit.setOnClickListener {
            val token = changeProfileViewModel.getToken()
            val userId = changeProfileViewModel.getUserId()
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etNumberPhone.text.toString()

            changeProfileViewModel.editUserData(token, userId, name, email, phoneNumber)
                .observe(this) { result ->
                    result.proceedWhen(
                        doOnSuccess = {
                            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()
                            displayProfileData()
                        },
                        doOnLoading = {
                        },
                        doOnError = {
                            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
                        }
                    )
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

    private fun getFileFromImageView(imageView: ImageView): MultipartBody.Part? {
        val drawable = imageView.drawable
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val file = createTempFile("temp_photo", ".jpg")
            file.outputStream().use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
            val requestBody =
                RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
            return MultipartBody.Part.createFormData("photo", file.name, requestBody)
        }
        return null
    }
}
