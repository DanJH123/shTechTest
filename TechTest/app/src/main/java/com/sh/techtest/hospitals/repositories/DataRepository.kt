package com.sh.techtest.hospitals.repositories

interface DataRepository {
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

    suspend fun getHospitalData(): HospitalListResponse

}