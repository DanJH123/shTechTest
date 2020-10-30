package com.sh.techtest.hospitals.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.sh.techtest.R
import com.sh.techtest.hospitals.domain.DomainHospital
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class HospitalInfoFragmentTest  {

    private lateinit var hospital: DomainHospital

    @Before
    fun setupHospital(){
        hospital = DomainHospital(
            17967, "NDA04", "Hospital", "UNKNOWN", "Independent Sector", "Visible", true, "Musculoskeletal physiotherapy service - Cranleigh Village Hospital", "", "6 High Street", "", "Cranleigh", "Surrey", "GU6 8AE", 51.14077377319336, -0.4865259528160095, "NDA", "Virgin Care Services Ltd", "01483 782400", "", "", ""
        )
    }

    @Test
    fun hospitalInfoTitle_DisplayedInUi(){
        val bundle = Bundle().apply{
            // Magic string representation of argument key in nav_graph
            putParcelable("hospital", hospital)
        }
        launchFragmentInContainer<HospitalInfoFragment>(bundle, R.style.Theme_MyApplication)
        onView(withId(R.id.organisation_name)).check(matches(withText(hospital.organisationName)))
    }

}