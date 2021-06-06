package com.hera.bangkit.ui.main.profile.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.databinding.FragmentReportBinding
import com.hera.bangkit.ui.main.post.report.ReportViewModel
import com.hera.bangkit.ui.main.profile.story.StoryFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportFragment : Fragment() {
    companion object{
        private const val FULLNAME = "fullname"

        fun newInstance(fullname:String): ReportFragment {
            val fragment = ReportFragment()
            val bundle = Bundle()
            bundle.putString(FULLNAME,fullname)
            fragment.arguments = bundle
            return fragment
        }
    }
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
            val fullname = arguments?.getString(FULLNAME)
            if (fullname != null) {
                viewModel.getReport(fullname).observe(viewLifecycleOwner,::setList)
            }
            binding.rvReportList.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setList(items: ArrayList<ReportEntity>) {
        if(items.isEmpty()) {
            binding.reportNotFound.isVisible = true
        } else {
            binding.reportNotFound.isVisible = false
        }

        adapter = ReportAdapter(items)
        binding.rvReportList.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}