package com.project.skypass.presentation.profile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.project.skypass.R
import com.project.skypass.databinding.ActivityChangeProfileBinding
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
    }

    private fun displayProfileData() {
        /*val profiles = changeProfileViewModel.getProfile()
        profiles.firstOrNull()?.let { profile ->
            binding.etName.setText(profile.name)
            binding.etEmail.setText(profile.email)
            binding.etNumberPhone.setText(profile.phoneNumber)
            binding.ivProfile.load(profile.photoUrl)
        }*/
        val userId = changeProfileViewModel.getUserId()
        changeProfileViewModel.showDataUser(userId).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.ivProfile.load(it.payload?.photoUrl)
                    binding.etName.setText(it.payload?.name)
                    binding.etEmail.setText(it.payload?.email)
                    binding.etNumberPhone.setText(it.payload?.phoneNumber)
                },
                doOnLoading = {

                },
                doOnError = {

                }
            )
        }
    }
}
