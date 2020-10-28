package com.sh.techtest.hospitals.ui

import com.sh.techtest.hospitals.domain.DomainHospital

/** For use when displaying hospital data */
class DisplayHospital(
    val organisationID: String,
    val organisationCode: String,
    val organisationType: String,
    val subType: String,
    val sector: String,
    val organisationStatus: String,
    val isPimsManaged: String,
    val organisationName: String,
    val address1: String,
    val address2: String,
    val address3: String,
    val city: String,
    val county: String,
    val postcode: String,
    val coordinates: String,
    val parentODSCode: String,
    val parentName: String,
    val phone: String,
    val email: String,
    val website: String,
    val fax: String,
)

/** Convert all values in [DomainHospital] to a UI-friendly string */
fun DomainHospital.asDisplay(): DisplayHospital {
    return DisplayHospital(
        "$organisationID",
        organisationCode,
        organisationType,
        subType,
        sector,
        organisationStatus,
        "$isPimsManaged",
        organisationName,
        address1,
        address2,
        address3,
        city,
        county,
        postcode,
        "$latitude, $longitude",
        parentODSCode,
        parentName,
        phone,
        email,
        website,
        fax,
    )
}