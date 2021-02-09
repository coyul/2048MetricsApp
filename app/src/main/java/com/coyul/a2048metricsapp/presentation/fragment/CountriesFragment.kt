package com.coyul.a2048metricsapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.coyul.a2048metricsapp.R
import com.coyul.a2048metricsapp.databinding.FragmentCountriesBinding
import com.coyul.a2048metricsapp.presentation.adapter.CountriesAdapter
import com.coyul.a2048metricsapp.presentation.viewmodel.CountriesViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CountriesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CountriesViewModel
    private lateinit var adapter: CountriesAdapter
    private var binding: FragmentCountriesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CountriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_countries, container, false)

        binding = FragmentCountriesBinding.bind(root)
        observeViewModel()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CountriesAdapter()
        binding?.countriesRecyclerView?.adapter = adapter
        viewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observeViewModel() {
        viewModel.listLiveData.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        viewModel.progressLiveData.observe(viewLifecycleOwner, {
            if (it == true) binding?.progress?.visibility = View.VISIBLE
            else binding?.progress?.visibility = View.GONE
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            binding?.stubLayout?.stub?.visibility = View.VISIBLE
        })
    }
}