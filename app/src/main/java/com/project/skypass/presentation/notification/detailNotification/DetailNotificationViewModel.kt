package com.project.skypass.presentation.notification.detailNotification

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.Notification
import com.project.skypass.data.repository.notification.NotificationRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.presentation.notification.detailNotification.DetailNotificationActivity.Companion.EXTRA_NOTIFICATION
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class DetailNotificationViewModel(
    private val intent: Bundle?,
    private val prefRepository: PrefRepository,
    private val notificationRepository: NotificationRepository
): ViewModel() {

    val notificationData = intent?.getParcelable<Notification>(EXTRA_NOTIFICATION)

    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun detailNotification(
        token: String,
        id: String,
    ): LiveData<ResultWrapper<Notification>> {
        return notificationRepository.getDetailNotification(token, id).asLiveData(Dispatchers.IO)
    }
}