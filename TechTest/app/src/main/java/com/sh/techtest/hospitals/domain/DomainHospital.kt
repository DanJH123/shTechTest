package com.sh.techtest.hospitals.domain

import android.os.Parcelable
import com.sh.techtest.hospitals.network.NetworkHospital
import kotlinx.android.parcel.Parcelize

/** Version of a Hospital object that is used throughout the project. */
@Parcelize
class DomainHospital (
    val organisationID: Int,
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
    val latitude: Double,
    val longitude: Double,
    val parentODSCode: String,
    val parentName: String,
    val phone: String,
    val email: String,
    val website: String,
    val fax: String
): Parcelable

/**
 * This acts as an adapter for the Network and Domain models. If a change is made to the Network,
 * this function may be all that needs adjusting
 * */
fun NetworkHospital.asDomain(): DomainHospital {
    return DomainHospital(
        organisationID = organisationID,
        organisationCode = organisationCode,
        organisationType = organisationType,
        subType = subType,
        sector = sector,
        organisationStatus = organisationStatus,
        isPimsManaged = isPimsManaged,
        organisationName = organisationName,
        address1 = address1,
        address2 = address2,
        address3 = address3,
        city = city,
        county = county,
        postcode = postcode,
        latitude = latitude,
        longitude = longitude,
        parentODSCode = parentODSCode,
        parentName = parentName,
        phone = phone,
        email = email,
        website = website,
        fax = fax
    )
}
