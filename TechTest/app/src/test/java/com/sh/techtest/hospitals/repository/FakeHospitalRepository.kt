package com.sh.techtest.hospitals.repository

import com.sh.techtest.hospitals.repositories.DataRepository
import com.sh.techtest.hospitals.repositories.HospitalListResponse

/*TODO*/
class FakeHospitalRepository(): DataRepository {

    override suspend fun getHospitalData(): HospitalListResponse {
        TODO("Not yet implemented")
    }

}