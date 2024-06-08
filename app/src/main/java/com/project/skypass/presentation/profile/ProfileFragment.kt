package com.project.skypass.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.project.skypass.core.BaseActivity
import com.project.skypass.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setClickAction()
        displayProfileData()
    }

    private fun setClickAction() {
        binding.llSettingsAccount.setOnClickListener {
            val intent = Intent(activity, SettingsAccountActivity::class.java)
            startActivity(intent)
        }
        binding.llEditProfile.setOnClickListener {
            val intent = Intent(activity, ChangeProfileActivity::class.java)
            startActivity(intent)
        }
        binding.llLogout.setOnClickListener {
            logout()
        }
    }

    private fun displayProfileData() {
        val profiles = profileViewModel.getProfile()
        profiles.firstOrNull()?.let { profile ->
            binding.tvNameProfile.text = profile.name
            binding.tvEmailProfile.text = profile.email
            binding.tvNumberPhoneProfile.text = profile.phoneNumber
            binding.ivProfile.load(profile.photoUrl)
        }
    }

    private fun logout() {
        (activity as BaseActivity).handleUnAuthorize()
    }
}
