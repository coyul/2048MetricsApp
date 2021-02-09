package com.coyul.a2048metricsapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.coyul.a2048metricsapp.R
import com.coyul.a2048metricsapp.databinding.FragmentMainMetricsBinding
import com.coyul.a2048metricsapp.presentation.model.FIRST_PERIOD
import com.coyul.a2048metricsapp.presentation.model.UiPeriod
import com.coyul.a2048metricsapp.presentation.viewmodel.MainMetricsViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MainMetricsFragment : DaggerFragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainMetricsViewModel
    private var binding: FragmentMainMetricsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MainMetricsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_main_metrics, container, false)
        binding = FragmentMainMetricsBinding.bind(root)
        observeViewModel()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpSpinner()
        viewModel.firstLoad()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observeViewModel() {
        viewModel.salesDataLiveData.observe(viewLifecycleOwner, {
            binding?.downloadsText?.text = String.format(
                getString(R.string.downloads_format),
                it.downloads
            )
            binding?.revenueText?.text = String.format(
                getString(R.string.revenue_format),
                it.revenue
            )
        })
        viewModel.salesDataBarSetLiveData.observe(viewLifecycleOwner, {
            configureChart(binding?.downloadsGraphic, it.first)
            configureChart(binding?.revenueGraphic, it.second)
        })
        viewModel.progressTextLiveData.observe(viewLifecycleOwner, {
            binding?.downloadsProgress?.visibility = if (it == true) View.VISIBLE else View.GONE
            binding?.revenueProgress?.visibility = if (it == true) View.VISIBLE else View.GONE
        })
        viewModel.progressBarLiveData.observe(viewLifecycleOwner, {
            binding?.downloadsGraphicProgress?.visibility =
                if (it == true) View.VISIBLE else View.GONE
            binding?.revenueGraphicProgress?.visibility =
                if (it == true) View.VISIBLE else View.GONE
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            binding?.stubLayout?.stub?.visibility = View.VISIBLE
        })
    }

    private fun setUpSpinner() {
        val spinnerList = mutableListOf(FIRST_PERIOD)
        UiPeriod.values().iterator().forEach {
            spinnerList.add(String.format(
                        getString(R.string.days_filter_format),
                        it.daysPeriod))}
        context?.let { context ->
            ArrayAdapter(context, android.R.layout.simple_spinner_item, spinnerList)
                .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding?.spinner?.adapter = adapter }
        }
        binding?.spinner?.onItemSelectedListener = this
    }

    private fun configureChart(chart: BarChart?, dataSet: BarDataSet) {
        dataSet.color = R.color.purple_500
        val data = BarData(dataSet)
        data.barWidth = 80000f
        chart?.data = data
        chart?.minimumHeight = 500
        chart?.legend?.isEnabled = false
        chart?.description = null
        chart?.axisLeft?.setDrawLabels(false)
        chart?.axisRight?.setDrawLabels(false)
        chart?.xAxis?.setDrawLabels(false)
        chart?.setFitBars(true)
        chart?.invalidate()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.onDateFilterClicked(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //nothing need to be done
    }
}