package com.coyul.a2048metricsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coyul.a2048metricsapp.R
import com.coyul.a2048metricsapp.databinding.CountryListItemBinding
import com.coyul.a2048metricsapp.domain.model.CountrySalesData

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    private var countriesList: List<CountrySalesData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CountryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = countriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(countriesList[position])

    fun submitList(list: List<CountrySalesData>) {
        this.countriesList = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: CountryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(countrySalesData: CountrySalesData) {
            binding.countryName.text = countrySalesData.countryName
            Glide
                .with(itemView)
                .load(countrySalesData.countryImageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_stub_image)
                .into(binding.countryImage)

            binding.countryDownloads.text = countrySalesData.downloads.toString()
            binding.countryRevenue.text = countrySalesData.revenue.toString()
        }
    }
}