package com.project.skypass.presentation.profile.changeprofile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.project.skypass.databinding.ActivityChangeProfileBinding
import com.project.skypass.presentation.main.MainActivity
import com.project.skypass.utils.proceedWhen
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

        binding.ivEditPhoto.setOnClickListener {
            openGalleryForImage()
        }

        binding.btnEdit.setOnClickListener {
            val token = changeProfileViewModel.getToken()
            val userId = changeProfileViewModel.getUserId()
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etNumberPhone.text.toString()
            val photo = changeProfileViewModel.profilePhotoUri.value

            changeProfileViewModel.editUserData(token, userId, name, email, phoneNumber, photo)
                .observe(this) { result ->
                    result.proceedWhen(
                        doOnSuccess = {
                            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()

                            // Set result to indicate profile update success
                            setResult(Activity.RESULT_OK)

                            // Finish activity to return to the previous screen
                            finish()
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

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.data?.let { uri ->
                binding.ivProfile.setImageURI(uri)
                changeProfileViewModel.setProfilePhoto(uri)
            }
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }
}
