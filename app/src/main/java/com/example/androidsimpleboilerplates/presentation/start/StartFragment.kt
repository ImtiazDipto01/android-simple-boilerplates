package com.example.androidsimpleboilerplates.presentation.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidsimpleboilerplates.R
import com.example.androidsimpleboilerplates.core.base.BaseFragment
import com.example.androidsimpleboilerplates.databinding.FragmentStartBinding

class StartFragment : BaseFragment<FragmentStartBinding>() {

    override fun getViewBinding(): FragmentStartBinding = FragmentStartBinding.inflate(layoutInflater)

    //private val viewModel: StartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //observePicInfo()
    }

}