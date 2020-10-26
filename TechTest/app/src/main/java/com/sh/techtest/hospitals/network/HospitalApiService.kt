package com.sh.techtest.hospitals.network

/** TODO:
 * - Set up moshi and retrofit builders
 * - Create globally accessible implementation of [HospitalApiService]
 * */


/**
 * Interface that defines the method(s) for interacting with the specified API
 * */
interface HospitalApiService {

    //todo: complete this signature for retrofit request to get list of hospitals
    fun getHospitalsAsync()

}