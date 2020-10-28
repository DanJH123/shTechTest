package com.sh.techtest.hospitals.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.sh.techtest.hospitals.domain.DomainHospital
import com.sh.techtest.hospitals.domain.asDomain
import com.sh.techtest.hospitals.network.HospitalApi
import retrofit2.HttpException

/**
 *  This class will be the source of data for the hospital-related queries.
 *  */
class HospitalsRepository(){

    companion object {
        /**
         * Response was a success
         * */
        const val SUCCESS = 1

        /**
         * Response failed
         * */
        const val FAIL = 2
    }

    /**
     * Wrapper class for hospital API response. Allows us to return any error code along with possible
     * data
     *
     * responseKey: will be a key taken from [HospitalsRepository] companion object
     * responseList: List of domain objects
     * */
    class HospitalListResponse(val responseKey: Int, val responseList: List<DomainHospital>?)

    /**
     * Publicly accessible list of hospitals to be set once getHospitalData has been successfully
     * executed
     * */
    var hospitalsList: List<DomainHospital>? = null


    /**
     * Calls API to get list of hospitals
     * @return [HospitalListResponse] containing basic success/error codes with the list or null
     * */
    suspend fun getHospitalData(): HospitalListResponse {
        return try{
            //perform request
            val result = HospitalApi.service.getHospitals()

            //convert to domain list and assign to property
            hospitalsList = result.map { it.asDomain() }

            //Wrap results with code and return
            HospitalListResponse(SUCCESS, hospitalsList)
        }
        catch (h: HttpException){
            h.printStackTrace()
            //Basic error code returned with null list
            HospitalListResponse(FAIL, null)
        }
        catch (e: Exception){
            e.printStackTrace()
            //Basic error code returned with null list
            HospitalListResponse(FAIL, null)
        }
    }


}