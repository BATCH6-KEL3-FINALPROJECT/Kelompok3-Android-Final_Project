package com.project.skypass.presentation.profile.changeprofile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.data.source.network.model.user.edituser.EditUserResponse
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import java.io.File


class ChangeProfileViewModelExample(
    private val prefRepository: PrefRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _profilePhotoUri = MutableLiveData<Uri>()
    val profilePhotoUri: LiveData<Uri> get() = _profilePhotoUri

    fun editUserData(
        token: String,
        id: String,
        name: String,
        email: String,
        phoneNumber: String,
        photo: File? = null
    ): LiveData<ResultWrapper<EditUserResponse>> {
        return userRepository.editUser(token, id, name, email, phoneNumber, photo).asLiveData(Dispatchers.IO)
    }

    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun getUserId(): String {
        return prefRepository.getUserID()
    }

    fun showDataUser(id: String): LiveData<ResultWrapper<User>> {
        return userRepository.getUser(id).asLiveData(Dispatchers.IO)
    }

    fun setProfilePhoto(uri: Uri) {
        _profilePhotoUri.value = uri
    }
}