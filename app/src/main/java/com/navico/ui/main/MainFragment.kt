package com.navico.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.navico.databinding.MainFragmentBinding
import com.navico.ui.adapter.MainListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var  mainFragmentBinding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = MainFragmentBinding.inflate(layoutInflater)
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MainListAdapter()
        adapter.setActivity(requireActivity())

        mainFragmentBinding.mainList.layoutManager = LinearLayoutManager(activity)
        mainFragmentBinding.mainList.adapter = adapter
        context?.let { viewModel.getAndShowPoints(adapter, it) }
    }
}
