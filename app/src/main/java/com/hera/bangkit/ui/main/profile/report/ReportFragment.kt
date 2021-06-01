package com.hera.bangkit.ui.main.profile.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.databinding.FragmentReportBinding
import com.hera.bangkit.ui.main.post.report.ReportViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportFragment : Fragment() {

    private val viewModel : ReportViewModel by activityViewModels()
    private var _binding: FragmentReportBinding? = null
    private lateinit var adapter: ReportAdapter
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentReportBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.let {
            binding.rvReportList.layoutManager = LinearLayoutManager(requireContext())
            viewModel.getReport().observe(viewLifecycleOwner,::setList)
        }


    }

    private fun setList(items: ArrayList<ReportEntity>) {
        adapter = ReportAdapter(items)
        binding.rvReportList.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}