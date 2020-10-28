package com.sh.techtest.hospitals.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sh.techtest.R
import com.sh.techtest.databinding.FragmentHospitalListBinding
import com.sh.techtest.hospitals.domain.DomainHospital
import com.sh.techtest.hospitals.viewmodels.HospitalsViewModel
import com.sh.techtest.hospitals.viewmodels.HospitalsViewModel.SubTypeFilter.*

/**
 * - Displays UI containing the list of hospitals
 * - Clicking on a hospital will navigate to [HospitalInfoFragment]
 * - Options menu is used for filtering the list
 * - Uses [HospitalsViewModel]
 *  */
class HospitalListFragment : Fragment(), HospitalListAdapter.OnItemClickListenerCallback {

    private lateinit var binding: FragmentHospitalListBinding
    private lateinit var viewModel: HospitalsViewModel
    private lateinit var adapter: HospitalListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** We use the options menu for filtering purposes*/
        setHasOptionsMenu(true)

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

        binding.retryBtn.setOnClickListener { viewModel.fetchHospitalData() }

        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                HospitalsViewModel.HospitalApiStatus.LOADING -> {
                    binding.errorMessage.visibility = View.GONE
                    binding.retryBtn.visibility = View.GONE
                    binding.loadingIndicator.visibility = View.VISIBLE
                }
                HospitalsViewModel.HospitalApiStatus.ERROR -> {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.retryBtn.visibility = View.VISIBLE
                    binding.loadingIndicator.visibility = View.GONE
                }
                else -> {
                    binding.errorMessage.visibility = View.GONE
                    binding.retryBtn.visibility = View.GONE
                    binding.loadingIndicator.visibility = View.GONE
                }
            }
        }

        viewModel.hospitalsList.observe(viewLifecycleOwner){
            it?.let {
                adapter.submitList(it)
            }
        }

    }

    /**
     * Callback function for when a hospital list item has been clicked. This will lead the user
     * to the [HospitalInfoFragment] and pass along the information as an argument for the fragment
     * to unwrap
     * */
    override fun onItemClicked(hospital: DomainHospital) {
        findNavController().navigate(HospitalListFragmentDirections.actionHospitalListFragmentToHospitalInfoFragment(hospital))
    }

    /** Apply all the functions necessary to display the hospitals recycler view*/
    private fun initialiseRecyclerView(
        context: Context, itemClickListener: HospitalListAdapter.OnItemClickListenerCallback
    ) {
        adapter = HospitalListAdapter(context, itemClickListener)

        // Scroll to top whenever a change is made to the list.
        adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                binding.hospitalsRv.scrollToPosition(0)
            }
        })

        binding.hospitalsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HospitalListFragment.adapter
        }
    }

    /**
     * Inflate menu layout and set up observer for the filter to display which one is currently
     * shown.
     * */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        viewModel.filter.observe(viewLifecycleOwner){
            it?.let {
                menu.apply {
                    findItem(R.id.show_all_item)?.isChecked = it == NONE
                    findItem(R.id.show_unknown_item)?.isChecked = it == UNKNOWN
                    findItem(R.id.show_mental_health_item)?.isChecked = it == MENTAL_HEALTH
                    findItem(R.id.show_hospital_item)?.isChecked = it == HOSPITAL
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_hospital_item -> HOSPITAL
                R.id.show_mental_health_item -> MENTAL_HEALTH
                R.id.show_unknown_item -> UNKNOWN
                else -> NONE
            }
        )
        return true
    }



}