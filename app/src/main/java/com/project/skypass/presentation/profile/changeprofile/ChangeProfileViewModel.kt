package com.project.skypass.presentation.profile.changeprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.Response
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.profile.ProfileRepository
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.data.source.network.model.user.edituser.Data
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import java.io.File

class ChangeProfileViewModel(
    private val prefRepository: PrefRepository,
    private val userRepository: UserRepository,
    ) : ViewModel() {
    fun editUserData(
        token: String,
        id: String,
        name: String,
        email: String,
        phoneNumber: String,
        photo: File? = null,
    ): LiveData<ResultWrapper<Response<Data>>> {
        return userRepository.editUser(token, id, name, email, phoneNumber, photo).asLiveData(
            Dispatchers.IO)
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
}
