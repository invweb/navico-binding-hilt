package com.navico.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.navico.R
import com.navico.databinding.MainFragmentBinding
import com.navico.ui.adapter.MainListAdapter

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var  mainFragmentBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = MainFragmentBinding.inflate(layoutInflater)
        return mainFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val adapter = MainListAdapter()
        adapter.setActivity(requireActivity())
        mainFragmentBinding.mainList.layoutManager = LinearLayoutManager(activity)
        mainFragmentBinding.mainList.adapter = adapter
        viewModel.getAndShowPoints(adapter)
    }
}
