package com.project.skypass.presentation.profile.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.project.skypass.core.BaseActivity
import com.project.skypass.databinding.FragmentProfileBinding
import com.project.skypass.presentation.profile.settingaccount.SettingsAccountActivity
import com.project.skypass.presentation.profile.changeprofile.ChangeProfileActivity
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModelExample by viewModel()

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
            showLogoutDialog()
        }
    }

    private fun displayProfileData() {
        /*val profiles = profileViewModel.getProfile()
        profiles.firstOrNull()?.let { profile ->
            binding.tvNameProfile.text = profile.name
            binding.tvEmailProfile.text = profile.email
            binding.tvNumberPhoneProfile.text = profile.phoneNumber
            binding.ivProfile.load(profile.photoUrl)
        }*/
        val userId = profileViewModel.getUserId()
        profileViewModel.showDataUser(userId).observe(viewLifecycleOwner){
            it.proceedWhen(
                doOnSuccess = {
                    binding.tvNameProfile.text = it.payload?.name
                    binding.tvEmailProfile.text = it.payload?.email
                    binding.tvNumberPhoneProfile.text = it.payload?.phoneNumber
                    binding.ivProfile.load(it.payload?.photoUrl)
                },
                doOnLoading = {

                },
                doOnError = {

                }
            )
        }
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setMessage("Apakah kamu yakin ingin keluar?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            logout()
            deleteOrderHistoryUser()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteOrderHistoryUser() {
        profileViewModel.deleteOrderHistoryUser().observe(viewLifecycleOwner) {
            it.proceedWhen(doOnSuccess = {
                Toast.makeText(requireContext(), "Menghapus Riwayat Pemesanan", Toast.LENGTH_SHORT).show()
            }, doOnLoading = {

            }, doOnError = { err ->
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun logout() {
        (activity as BaseActivity).handleUnAuthorize()
    }
}

