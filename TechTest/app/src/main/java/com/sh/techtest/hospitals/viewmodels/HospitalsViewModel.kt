package com.sh.techtest.hospitals.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sh.techtest.hospitals.ui.HospitalListFragment

/**
 * This class takes care of the business logic and provides data for the UI
 * It will access [HospitalsRepository] for data
 * */
class HospitalsViewModel(app: Application): AndroidViewModel(app){

    //
    private val _hospitalsList = MutableLiveData<List<HospitalListFragment>>()
    val hospitalsList: LiveData<List<HospitalListFragment>>
    get() = _hospitalsList



}