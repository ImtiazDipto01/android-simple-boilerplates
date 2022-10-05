package com.example.androidsimpleboilerplates.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getPicInfoUseCase: GetUsersUseCase
) : ViewModel() {

    var picInfoFlow: Flow<Resource<List<GithubUserResponse>>> =
        MutableStateFlow<Resource<List<GithubUserResponse>>>(Resource.Empty())

    init {
        viewModelScope.launch {
            picInfoFlow = getPicInfoUseCase.execute()
        }
    }

}