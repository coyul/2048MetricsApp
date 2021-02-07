package com.coyul.a2048metricsapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coyul.a2048metricsapp.R
import com.coyul.a2048metricsapp.presentation.viewmodel.MainMetricsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainMetricsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainMetricsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MainMetricsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_main_metrics, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        observeViewModel()
        return root
    }

    private fun observeViewModel() {
        //TODO
    }
}