package com.hera.bangkit.ui.main.hastag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.data.response.HastagEntity
import com.hera.bangkit.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var adapter: HastagAdapter
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHastag.layoutManager = LinearLayoutManager(requireContext())
        val arrHastag = arrayOf("Lokasi Publik","Lokasi Privat","Lokasi Kerja","Siber","Lokasi Pendidikan","Netral")
        viewModel.getHastag(arrHastag).observe(viewLifecycleOwner, ::getHastagList)

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