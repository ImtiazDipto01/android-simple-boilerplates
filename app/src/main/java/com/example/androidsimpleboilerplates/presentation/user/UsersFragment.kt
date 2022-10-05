package com.example.androidsimpleboilerplates.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var _binding: FragmentUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePicInfo()
    }

    private fun observePicInfo() {
        lifecycleScope.launchWhenStarted {
            viewModel.usersFlow.collect {
                when (it) {
                    is Resource.Loading -> {
                        _binding.pbLoading.isVisible = true
                    }
                    is Resource.Success -> {
                        _binding.pbLoading.isVisible = false
                        Timber.e("LoginFragment: ${it.data}")
                    }
                    is Resource.Error -> {
                        _binding.pbLoading.isVisible = false
                    }
                    else -> Unit
                }
            }
        }
    }

}