package com.sh.techtest.hospitals.repositories

import com.sh.techtest.hospitals.domain.DomainHospital

/**
 * Wrapper class for hospital API response. Allows us to return any error code along with possible
 * data
 *
 * responseKey: will be a key taken from [HospitalsRepository] companion object
 * responseList: List of domain objects
 * */
class HospitalListResponse(val responseKey: Int, val responseList: List<DomainHospital>?)