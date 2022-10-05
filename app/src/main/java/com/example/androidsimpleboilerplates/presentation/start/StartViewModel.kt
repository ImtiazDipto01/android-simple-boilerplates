package com.example.androidsimpleboilerplates.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.domain.model.PicInfo
import com.example.androidsimpleboilerplates.domain.usecase.GetPicInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val getPicInfoUseCase: GetPicInfoUseCase
) : ViewModel() {

    var picInfoFlow: Flow<Resource<List<GithubUserResponse>>> =
        MutableStateFlow<Resource<List<GithubUserResponse>>>(Resource.Empty())

    init {
        viewModelScope.launch {
            picInfoFlow = getPicInfoUseCase.execute()
        }
    }

}