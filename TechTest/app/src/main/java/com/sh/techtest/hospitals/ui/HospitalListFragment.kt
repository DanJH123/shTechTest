package com.sh.techtest.hospitals.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sh.techtest.R
import com.sh.techtest.databinding.FragmentHospitalListBinding
import com.sh.techtest.hospitals.viewmodels.HospitalsViewModel

/**
 * Displays UI containing the list of fragments
 *  */
class HospitalListFragment : Fragment() {

    private lateinit var binding: FragmentHospitalListBinding
    private lateinit var viewModel: HospitalsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHospitalListBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HospitalsViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialiseRecyclerView(requireContext())

        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                HospitalsViewModel.HospitalApiStatus.LOADING -> {/*todo*/}
                HospitalsViewModel.HospitalApiStatus.SUCCESS -> {/*todo*/}
                HospitalsViewModel.HospitalApiStatus.ERROR -> {/*todo*/}
                else -> {/*todo*/}
            }
        }


    }

    private fun initialiseRecyclerView(requireContext: Context) {
        //todo
    }

}