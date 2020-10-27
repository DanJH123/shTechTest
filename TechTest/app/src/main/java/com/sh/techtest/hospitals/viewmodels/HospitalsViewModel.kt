package com.sh.techtest.hospitals.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sh.techtest.hospitals.domain.DomainHospital
import com.sh.techtest.hospitals.repositories.HospitalsRepository
import com.sh.techtest.hospitals.ui.HospitalListFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * This class takes care of the business logic and provides data for the UI
 * It will access [HospitalsRepository] for data
 * */
class HospitalsViewModel(app: Application): AndroidViewModel(app){

    enum class HospitalApiStatus{
        LOADING,
        ERROR,
        SUCCESS
    }

    private val repository = HospitalsRepository()

    private val hospitalsJob = Job()
    private val hospitalsScope = CoroutineScope(Dispatchers.IO + hospitalsJob)

    // Internal and external access to hospitalsList. Only mutable within this class
    private val _hospitalsList = MutableLiveData<List<DomainHospital>>()
    val hospitalsList: LiveData<List<DomainHospital>>
    get() = _hospitalsList


    // Internal and external access to status. Only mutable within this class
    private val _status = MutableLiveData<HospitalApiStatus>()
    val status: LiveData<HospitalApiStatus>
    get() = _status

    init {
        fetchHospitalData()
    }

    /**
     * - Launch thread to contact repository for hospital data and update observable [hospitalsList]
     * - Update status throughout process
     * */
    fun fetchHospitalData(){
        _status.value = HospitalApiStatus.LOADING
        hospitalsScope.launch {

            val result = repository.getHospitalData()

            when(result.responseKey){
                HospitalsRepository.SUCCESS -> {
                    _hospitalsList.postValue(result.responseList)
                    _status.postValue(HospitalApiStatus.SUCCESS)
                }
                else -> {
                    _status.postValue(HospitalApiStatus.ERROR)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        hospitalsJob.cancel()
    }

}