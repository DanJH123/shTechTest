package com.sh.techtest.hospitals.repositories

import com.sh.techtest.hospitals.domain.DomainHospital
import com.sh.techtest.hospitals.domain.asDomain
import com.sh.techtest.hospitals.network.HospitalApi
import com.sh.techtest.hospitals.repositories.DataRepository.Companion.FAIL
import com.sh.techtest.hospitals.repositories.DataRepository.Companion.SUCCESS
import retrofit2.HttpException

/**
 *  This class will be the source of data for the hospital-related queries.
 *  */
class HospitalsRepository: DataRepository {

    /**
     * Publicly accessible list of hospitals to be set once getHospitalData has been successfully
     * executed.
     * Not mutable outside of this class
     * */
    private var _hospitalsList: List<DomainHospital>? = null
    val hospitalsList: List<DomainHospital>?
        get() = _hospitalsList

    /**
     * Calls API to get list of hospitals
     * @return [HospitalListResponse] containing basic success/error codes with the list or null
     * */
    override suspend fun getHospitalData(): HospitalListResponse {
        return try{
            //perform request
            val result = HospitalApi.service.getHospitals()

            //convert to domain list and assign to property
            _hospitalsList = result.map { it.asDomain() }

            //Wrap results with code and return
            HospitalListResponse(SUCCESS, _hospitalsList)
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