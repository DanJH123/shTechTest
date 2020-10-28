package com.sh.techtest.hospitals.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sh.techtest.R
import com.sh.techtest.databinding.ListItemHospitalBinding
import com.sh.techtest.hospitals.domain.DomainHospital

class HospitalListAdapter(
    private val context: Context, private val onItemClickListener: OnItemClickListenerCallback
) : ListAdapter<DomainHospital, HospitalListAdapter.HospitalViewHolder>(
    HospitalListDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder.from(parent, context, onItemClickListener)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HospitalViewHolder(
        private val binding: ListItemHospitalBinding,
        private val onItemClickListener: OnItemClickListenerCallback
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                parent: ViewGroup, context: Context, onItemClickListener: OnItemClickListenerCallback
            ): HospitalViewHolder{
                val layoutInflater = LayoutInflater.from(context)
                val binding =
                    ListItemHospitalBinding.inflate(layoutInflater, parent, false)
                return HospitalViewHolder(binding, onItemClickListener)
            }
        }

        fun bind(hospital: DomainHospital){
            binding.apply {

                organisationId.text = "${hospital.organisationID}"
                organisationName.text = hospital.organisationName
                location.text = getLocationText(hospital.city, hospital.county)
                organisationCode.text = hospital.organisationCode

                pimsValue.setImageResource(getPimsImageResource(hospital.isPimsManaged))

                listItem.setOnClickListener { onItemClickListener.onItemClicked(hospital) }
            }
        }

        private fun getPimsImageResource(pimsManaged: Boolean): Int {
            return if(pimsManaged) R.drawable.ic_check_circle
            else R.drawable.ic_cross
        }

        /** Generate the location text, handling empty string cases. */
        private fun getLocationText(city: String, county: String): CharSequence? {
            return when {
                city.isEmpty() -> county //if country is empty, it will return an empty string as expected
                county.isEmpty() -> city
                else -> "$city, $county"
            }
        }
    }

    interface OnItemClickListenerCallback {
        fun onItemClicked(hospital: DomainHospital)
    }

    /** This will be noticeable when changes are made to the list, e.g. filtering*/
    class HospitalListDiffCallback: DiffUtil.ItemCallback<DomainHospital>() {

        /** OrganisationID can be used as unique identifier between hospitals */
        override fun areItemsTheSame(oldItem: DomainHospital, newItem: DomainHospital): Boolean {
            return oldItem.organisationID == newItem.organisationID
        }

        /** Only a basic check is required here */
        override fun areContentsTheSame(oldItem: DomainHospital, newItem: DomainHospital): Boolean {
            return oldItem.organisationID == newItem.organisationID
        }

    }

}
