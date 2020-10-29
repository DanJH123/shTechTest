package com.sh.techtest.hospitals.viewmodels

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sh.techtest.getOrAwaitValue
import com.sh.techtest.hospitals.domain.DomainHospital
import org.junit.Assert.assertEquals
import org.junit.Assert.fail

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(maxSdk = Build.VERSION_CODES.P)
class HospitalsViewModelTest {

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var hospitalsViewModel: HospitalsViewModel
    lateinit var fakeData: List<DomainHospital>

    @Before
    fun setupViewModel(){
        hospitalsViewModel = HospitalsViewModel(ApplicationProvider.getApplicationContext())
    }
    @Before
    fun setupFakeDataForSubType(){
        fakeData = FakeData.getFakeSubTypeData() ?: ArrayList()
    }

    @Test
    /**
     * Test that the [filter] LiveData is correctly updated when calling [updateFilter]
     * */
    fun updateFilter_FilterIsUpdated_Hospital(){

        //Operation to be tested
        hospitalsViewModel.updateFilter(HospitalsViewModel.SubTypeFilter.HOSPITAL)

        //result
        val value: HospitalsViewModel.SubTypeFilter = hospitalsViewModel.filter.getOrAwaitValue()

        assertEquals(HospitalsViewModel.SubTypeFilter.HOSPITAL, value)
    }

    @Test
    /**
     * Test that the [filter] LiveData is correctly updated when calling [updateFilter]
     * */
    fun updateFilter_FilterIsUpdated_Unknown(){

        //Operation to be tested
        hospitalsViewModel.updateFilter(HospitalsViewModel.SubTypeFilter.UNKNOWN)

        //result
        val value: HospitalsViewModel.SubTypeFilter = hospitalsViewModel.filter.getOrAwaitValue()

        assertEquals(HospitalsViewModel.SubTypeFilter.UNKNOWN, value)
    }

    @Test
    /**
     * Test that the [filter] LiveData is correctly updated when calling [updateFilter]
     * */
    fun updateFilter_FilterIsUpdated_MentalHealth(){

        //Operation to be tested
        hospitalsViewModel.updateFilter(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH)

        //result
        val value: HospitalsViewModel.SubTypeFilter = hospitalsViewModel.filter.getOrAwaitValue()

        assertEquals(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH, value)
    }

    @Test
    /**
     * Test that the [filter] LiveData is correctly updated when calling [updateFilter]
     * */
    fun updateFilter_FilterIsUpdated_None(){

        //Operation to be tested
        hospitalsViewModel.updateFilter(HospitalsViewModel.SubTypeFilter.NONE)

        //result
        val value: HospitalsViewModel.SubTypeFilter = hospitalsViewModel.filter.getOrAwaitValue()

        assertEquals(HospitalsViewModel.SubTypeFilter.NONE, value)
    }

    @Test
    /**
     * Test the viewmodel function that updates and applies the filter to the hospital data list
     *
     * Expected: All items in remaining list should contain [HospitalsViewModel.SubTypeFilter.HOSPITAL.key]
     * as the sub type
     * */
    fun updateFilter_FilterShowsHospitalsOnly() {
        //data loaded in [setupFakeData]
        assert(fakeData.isNotEmpty())

        //Operation to be tested
        val list =
            hospitalsViewModel.filterList(HospitalsViewModel.SubTypeFilter.HOSPITAL, fakeData)
        if (list == null) {
            fail()
        } else {
            for (item in list) {
                assertEquals(HospitalsViewModel.SubTypeFilter.HOSPITAL.key, item.subType)
            }
        }
    }

    @Test
    /**
     * Test the viewmodel function that updates and applies the filter to the hospital data list
     *
     * Expected: All items in remaining list should contain [HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH]
     * as the sub type
     * */
    fun updateFilter_FilterShowsMentalHealthOnly() {
        //data loaded in [setupFakeData]
        assert(fakeData.isNotEmpty())

        //Operation to be tested
        val list =
            hospitalsViewModel.filterList(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH, fakeData)
        if (list == null) {
            fail()
        } else {
            for (item in list) {
                assertEquals(HospitalsViewModel.SubTypeFilter.MENTAL_HEALTH.key, item.subType)
            }
        }
    }

    @Test
    /**
     * Test the viewmodel function that updates and applies the filter to the hospital data list
     *
     * Expected: All items in remaining list should contain [HospitalsViewModel.SubTypeFilter.UNKNOWN.key]
     * as the sub type
     * */
    fun updateFilter_FilterShowsUnknownOnly() {
        //data loaded in [setupFakeData]
        val fakeData = fakeData
        assert(fakeData.isNotEmpty())

        //Operation to be tested
        val list =
            hospitalsViewModel.filterList(HospitalsViewModel.SubTypeFilter.UNKNOWN, fakeData)
        if (list == null) {
            fail()
        } else {
            for (item in list) {
                assertEquals(HospitalsViewModel.SubTypeFilter.UNKNOWN.key, item.subType)
            }
        }
    }

    @Test
    /**
     * Test the viewmodel function when no filter is applied
     *
     * Expected: No items should be removed from list [HospitalsViewModel.SubTypeFilter.NONE]
     * as the sub type
     * */
    fun updateFilter_FilterShowsAllHospitals() {
        //data loaded in [setupFakeData]
        val fakeData = fakeData
        assert(fakeData.isNotEmpty())
        val size = fakeData.size

        //Operation to be tested
        val list =
            hospitalsViewModel.filterList(HospitalsViewModel.SubTypeFilter.NONE, fakeData)
        if (list == null) {
            fail()
        } else {
            assertEquals(size, list.size)
        }

    }




}