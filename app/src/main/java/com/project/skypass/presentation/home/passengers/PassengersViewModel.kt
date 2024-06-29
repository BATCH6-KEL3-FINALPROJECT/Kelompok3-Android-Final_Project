package com.project.skypass.presentation.home.passengers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PassengersViewModel : ViewModel() {
    val passengersInfantCountLiveData =
        MutableLiveData(0).apply {
            postValue(0)
        }

    val passengersChildrenCountLiveData =
        MutableLiveData(0).apply {
            postValue(0)
        }

    val passengersAdultCountLiveData =
        MutableLiveData(0).apply {
            postValue(0)
        }

    fun addInfantPassenger() {
        val currentPassengers = passengersInfantCountLiveData.value ?: 0
        passengersInfantCountLiveData.postValue(currentPassengers + 1)
    }

    fun minusInfantPassenger() {
        if (passengersInfantCountLiveData.value!! > 0) {
            val currentPassengers = passengersInfantCountLiveData.value ?: 0
            passengersInfantCountLiveData.postValue(currentPassengers - 1)
        }
    }

    fun addChildrenPassenger() {
        val currentPassengers = passengersChildrenCountLiveData.value ?: 0
        passengersChildrenCountLiveData.postValue(currentPassengers + 1)
    }

    fun minusChildrenPassenger() {
        if (passengersChildrenCountLiveData.value!! > 0) {
            val currentPassengers = passengersChildrenCountLiveData.value ?: 0
            passengersChildrenCountLiveData.postValue(currentPassengers - 1)
        }
    }

    fun addAdultPassenger() {
        val currentPassengers = passengersAdultCountLiveData.value ?: 0
        passengersAdultCountLiveData.postValue(currentPassengers + 1)
    }

    fun minusAdultPassenger() {
        if (passengersAdultCountLiveData.value!! > 0) {
            val currentPassengers = passengersAdultCountLiveData.value ?: 0
            passengersAdultCountLiveData.postValue(currentPassengers - 1)
        }
    }

    fun totalPassengersCount(): Int {
        val infantCount = passengersInfantCountLiveData.value ?: 0
        val childrenCount = passengersChildrenCountLiveData.value ?: 0
        val adultCount = passengersAdultCountLiveData.value ?: 0
        return infantCount + childrenCount + adultCount
    }

    fun passengersAdultCount(): Int {
        val adultCount = passengersAdultCountLiveData.value ?: 0
        return adultCount
    }

    fun passengersChildrenCount(): Int {
        val count = passengersChildrenCountLiveData.value ?: 0
        return count
    }

    fun passengersInfantCount(): Int {
        val count = passengersInfantCountLiveData.value ?: 0
        return count
    }
}
