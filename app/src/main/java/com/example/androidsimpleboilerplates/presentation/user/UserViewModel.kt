package com.example.androidsimpleboilerplates.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.domain.model.GithubUser
import com.example.androidsimpleboilerplates.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private var _userStateFlow: MutableStateFlow<Resource<List<GithubUser>>> =
        MutableStateFlow(Resource.Empty())
    val usersFlow = _userStateFlow.asStateFlow()

    fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.execute().collect {
                _userStateFlow.value = it
            }
        }
    }

}