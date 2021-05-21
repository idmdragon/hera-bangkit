package com.hera.bangkit.ui.main.post

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hera.bangkit.databinding.FragmentPostBinding
import com.hera.bangkit.ui.main.post.report.ReportActivity
import com.hera.bangkit.ui.main.post.sos.SosActivity
import com.hera.bangkit.ui.main.post.story.PostStoryActivity


class PostFragment : Fragment(){

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPostBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnReport.setOnClickListener{
                startActivity(Intent(requireContext(),ReportActivity::class.java))
            }
            btnSos.setOnClickListener{
                startActivity(Intent(requireContext(),SosActivity::class.java))
            }
            btnStory.setOnClickListener{
                startActivity(Intent(requireContext(),PostStoryActivity::class.java))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}