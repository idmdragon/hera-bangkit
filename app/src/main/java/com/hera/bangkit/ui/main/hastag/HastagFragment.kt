package com.hera.bangkit.ui.main.hastag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.data.response.HastagEntity
import com.hera.bangkit.databinding.FragmentHastagBinding
import com.hera.bangkit.vo.Resource
import com.hera.bangkit.vo.Status
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class HastagFragment : Fragment() {

    private val viewModel: HastagViewModel by activityViewModels()
    private lateinit var adapter: HastagAdapter
    private var _binding: FragmentHastagBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHastagBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHastag.layoutManager = LinearLayoutManager(requireContext())
        val arrHastag = arrayOf("Lokasi Publik","Lokasi Privat","Lokasi Kerja","Siber","Lokasi Pendidikan","Netral")
        viewModel.getHastag(arrHastag).observe(viewLifecycleOwner, ::getHastagList)

//        viewModel.getHastag(arrHastag).observe(viewLifecycleOwner, { hastag ->
//        if (hastag != null) {
//            when (hastag) {
//                is Status.LOADING -> binding.shimmer.visibility = View.VISIBLE
//                is Status.SUCCESS -> {
//                    binding.shimmer.visibility = View.GONE
//                    adapter.setData(hastag.data)
//                }
//                is Status.ERROR {
//                    binding.shimmer.visibility = View.GONE
//                    binding.viewError.root.visibility = View.VISIBLE
//                    binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
//                }
//            }
//        }
//        })
//    }


//            viewModel.getHastag(arrHastag).observe(viewLifecycleOwner,{  status ->
//                when (status) {
//                    Status.SUCCESS -> {
//                        it.data.let { item ->
//                            adapter.notifyDataSetChanged()
//                            binding.shimmer.stopShimmer()
//                            binding.shimmer.visibility = View.GONE
//                            binding.rvHastag.adapter = adapter
//                        }
//                    }
//                    Status.LOADING -> {
//                    }
//
//                    Status.ERROR -> {
//                        binding.shimmer.stopShimmer()
//                        Toast.makeText(requireContext(),"Error when Load a Data", Toast.LENGTH_LONG).show()

//                    }
//                }
//            })
//            binding.rvHastag.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        }

    private fun getHastagList(items: ArrayList<HastagEntity>) {
        adapter = HastagAdapter(items)
        adapter.notifyDataSetChanged()
        binding.rvHastag.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}