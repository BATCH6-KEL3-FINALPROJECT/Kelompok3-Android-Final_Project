package com.project.skypass.presentation.profile.changeprofile

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.project.skypass.databinding.ActivityChangeProfileBinding
import com.project.skypass.utils.ImagePath
import com.project.skypass.utils.ImagePath.getRealPathFromURI
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ChangeProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangeProfileBinding
    private val changeProfileViewModel: ChangeProfileViewModelExample by viewModel()
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null
    private var selectedImageFile: File? = null

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

            changeProfileViewModel.editUserData(token, userId, name, email, phoneNumber, selectedImageFile)
                .observe(this) { result ->
                    result.proceedWhen(
                        doOnSuccess = {
                            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()
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
}
