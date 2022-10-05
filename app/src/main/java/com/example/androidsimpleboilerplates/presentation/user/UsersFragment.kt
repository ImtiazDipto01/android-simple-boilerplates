package com.example.androidsimpleboilerplates.presentation.user

import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()

    private lateinit var userAdapter: UsersAdapter
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
        initRecyclerView()
    }

    private fun observePicInfo() {
        lifecycleScope.launchWhenStarted {
            viewModel.usersFlow.collect {
                when (it) {
                    is Resource.Loading -> {
                        _binding.pbLoading.isVisible = true
                    }
                    is Resource.Success -> {
                        Timber.e("UserFragment: ${it.data}")
                        _binding.apply {
                            pbLoading.isVisible = false
                            recyclerview.isVisible = true
                            userAdapter.submitList(it.data)
                        }
                    }
                    is Resource.Error -> {
                        _binding.pbLoading.isVisible = false
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun initRecyclerView() = _binding.apply {
        recyclerview.apply {
            userAdapter = UsersAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
        viewModel.getUsers()
    }
}