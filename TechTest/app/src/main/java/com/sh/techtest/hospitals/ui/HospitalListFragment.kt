package com.sh.techtest.hospitals.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sh.techtest.R
import com.sh.techtest.databinding.FragmentHospitalListBinding
import com.sh.techtest.hospitals.domain.DomainHospital
import com.sh.techtest.hospitals.viewmodels.HospitalsViewModel

/**
 * Displays UI containing the list of fragments
 *  */
class HospitalListFragment : Fragment(), HospitalListAdapter.OnItemClickListenerCallback {

    private lateinit var binding: FragmentHospitalListBinding
    private lateinit var viewModel: HospitalsViewModel
    private lateinit var adapter: HospitalListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** Required for vector drawables on older android versions (<21)*/
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

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

        initialiseRecyclerView(requireContext(), this)

        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                HospitalsViewModel.HospitalApiStatus.LOADING -> {/*todo*/}
                HospitalsViewModel.HospitalApiStatus.SUCCESS -> {/*todo*/}
                HospitalsViewModel.HospitalApiStatus.ERROR -> {/*todo*/}
                else -> {/*todo*/}
            }
        }

        viewModel.hospitalsList.observe(viewLifecycleOwner){
            it?.let {
                adapter.submitList(it)
            }
        }

    }

    override fun onItemClicked(hospital: DomainHospital) {
        findNavController().navigate(HospitalListFragmentDirections.actionHospitalListFragmentToHospitalInfoFragment(hospital))
    }

    private fun initialiseRecyclerView(
        context: Context, itemClickListener: HospitalListAdapter.OnItemClickListenerCallback
    ) {
        adapter = HospitalListAdapter(context, itemClickListener)
        binding.hospitalsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HospitalListFragment.adapter
        }
    }

}