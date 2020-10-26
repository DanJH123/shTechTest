package com.sh.techtest.hospitals.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkHospital(
    @Json(name = "OrganisationID") val organisationID: Int,
    @Json(name = "OrganisationCode") val organisationCode: String,
    @Json(name = "OrganisationType") val organisationType: String,
    @Json(name = "SubType") val subType: String,
    @Json(name = "Sector") val sector: String,
    @Json(name = "OrganisationStatus") val organisationStatus: String,
    @Json(name = "IsPimsManaged") val isPimsManaged: String,
    @Json(name = "OrganisationName") val organisationName: String,
    @Json(name = "Address1") val address1: String,
    @Json(name = "Address2") val address2: String,
    @Json(name = "Address3") val address3: String,
    @Json(name = "City") val city: String,
    @Json(name = "County") val county: String,
    @Json(name = "Postcode") val postcode: String,
    @Json(name = "Latitude") val latitude: Double,
    @Json(name = "Longitude") val longitude: Double,
    @Json(name = "ParentODSCode") val parentODSCode: String,
    @Json(name = "ParentName") val parentName: String,
    @Json(name = "Phone") val phone: String,
    @Json(name = "Email") val email: String,
    @Json(name = "Website") val website: String,
    @Json(name = "Fax") val fax: String
): Parcelable