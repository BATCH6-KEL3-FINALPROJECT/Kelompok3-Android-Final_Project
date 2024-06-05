package com.project.skypass.presentation.home.passengers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PassengersViewModel: ViewModel() {

    val passengersCountLiveData =
        MutableLiveData(0).apply {
            postValue(0)
        }

    fun addPassenger(){
        val currentPassengers = passengersCountLiveData.value ?: 0
        passengersCountLiveData.postValue(currentPassengers + 1)
    }

    fun minusPassenger(){
        if (passengersCountLiveData.value!! > 0){
            val currentPassengers = passengersCountLiveData.value ?: 0
            passengersCountLiveData.postValue(currentPassengers - 1)
        }
    }
}