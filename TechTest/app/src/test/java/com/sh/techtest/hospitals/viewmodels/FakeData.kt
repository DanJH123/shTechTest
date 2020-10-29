package com.sh.techtest.hospitals.viewmodels

import com.sh.techtest.hospitals.domain.DomainHospital

class FakeData {

    companion object {
        fun getFakeSubTypeData(): List<DomainHospital>? {

            return listOf(
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.HOSPITAL.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.HOSPITAL.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.UNKNOWN.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.UNKNOWN.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.HOSPITAL.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.UNKNOWN.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.HOSPITAL.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.HOSPITAL.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.UNKNOWN.key),
                getFakeSubTypeObject(HospitalsViewModel.SubTypeFilter.UNKNOWN.key),
            )
        }

        private fun getFakeSubTypeObject(subType: String): DomainHospital{
            return DomainHospital(
                17967,
                "NDA04",
                "Hospital",
                subType,
                "Independent Sector",
                "Visible",
                true,
                "Musculoskeletal physiotherapy service - Cranleigh Village Hospital",
                "",
                "6 High Street",
                "",
                "Cranleigh",
                "Surrey",
                "GU6 8AE",
                51.14077377319336,
                -0.4865259528160095,
                "NDA",
                "Virgin Care Services Ltd",
                "01483 782400",
                "",
                "",
                ""
            )
        }
    }

}