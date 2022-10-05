package com.example.androidsimpleboilerplates.presentation.start

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.example.androidsimpleboilerplates.R
import com.example.androidsimpleboilerplates.core.base.BaseFragment
import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.databinding.FragmentStartBinding
import com.example.androidsimpleboilerplates.domain.model.PicInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>() {

    override fun getViewBinding(): FragmentStartBinding = FragmentStartBinding.inflate(layoutInflater)

    private val viewModel: StartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePicInfo()
    }

    private fun observePicInfo() {
        lifecycleScope.launchWhenStarted {
            viewModel.picInfoFlow.collect {
                when (it) {
                    is Resource.Loading -> {
                        Timber.e("LoginFragment: PicInfo Loading")
                        updateUi(true, null)
                    }
                    is Resource.Success -> {
                        Timber.e("LoginFragment: ${it.data}")
                    }
                    is Resource.Error -> {
                        Timber.e("LoginFragment: ${it.throwable.message ?: ""}")
                        updateUi(false, null)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun updateUi(isLoading: Boolean, data: PicInfo? = null) {
        binding?.apply {
            pbLoading.isVisible = isLoading
            ivImg.isVisible = !isLoading
            tvImageName.isVisible = !isLoading

            if (!isLoading) {
                val options: RequestOptions = RequestOptions()
                    .transform(FitCenter())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH)

                Glide.with(ivImg.context)
                    .load(data?.downloadUrl)
                    .apply(options)
                    .into(ivImg)

                tvImageName.text = data?.author ?: ""
            }
        }
    }

}