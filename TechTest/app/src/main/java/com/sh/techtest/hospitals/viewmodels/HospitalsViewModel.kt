package com.sh.techtest.hospitals.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sh.techtest.hospitals.domain.DomainHospital
import com.sh.techtest.hospitals.repositories.HospitalsRepository
import com.sh.techtest.hospitals.viewmodels.HospitalsViewModel.SubTypeFilter.*
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

    enum class SubTypeFilter(val key: String) {
        NONE(""),
        HOSPITAL("Hospital"),
        MENTAL_HEALTH("Mental Health Hospital"),
        UNKNOWN("UNKNOWN"),

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

    // Internal and external access to filter. Only mutable within this class
    private val _filter = MutableLiveData<SubTypeFilter>()
    val filter: LiveData<SubTypeFilter>
    get() = _filter

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
                    updateFilter(_filter.value ?: NONE)
                    _status.postValue(HospitalApiStatus.SUCCESS)
                }
                else -> {
                    _status.postValue(HospitalApiStatus.ERROR)
                }
            }
        }
    }

    fun updateFilter(subTypeFilter: SubTypeFilter) {
        _filter.postValue(subTypeFilter)
        applyFilter(subTypeFilter, repository.hospitalsList)
    }

    private fun applyFilter(filter: SubTypeFilter, list: List<DomainHospital>?){
        if(list.isNullOrEmpty()) return

        _hospitalsList.postValue(when (filter) {
            HOSPITAL -> list.filter { it.subType == HOSPITAL.key }
            MENTAL_HEALTH -> list.filter { it.subType == MENTAL_HEALTH.key }
            UNKNOWN -> list.filter { it.subType == UNKNOWN.key }
            else -> list
        })

    }

    override fun onCleared() {
        super.onCleared()
        hospitalsJob.cancel()
    }

}