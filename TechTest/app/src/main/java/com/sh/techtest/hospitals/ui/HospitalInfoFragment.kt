package com.sh.techtest.hospitals.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sh.techtest.R
import com.sh.techtest.databinding.FragmentHospitalInfoBinding
import com.sh.techtest.hospitals.domain.DomainHospital

/**
 * - Displays UI containing information about a specific hospital
 * - Uses databinding to supply UI data upon receiving [DomainHospital] in arguments
 * - Must pass [DomainHospital] in arguments when navigating to this fragment
 * */
class HospitalInfoFragment : Fragment() {

    lateinit var binding: FragmentHospitalInfoBinding
    lateinit var hospital: DomainHospital

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_hospital_info, container, false
        )

        // Fetch [DomainHospital] from args and pass to layout
        binding.hospital = HospitalInfoFragmentArgs.fromBundle(requireArguments()).hospital.asDisplay()

        return binding.root
    }

}
