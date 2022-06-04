package com.example.androidsimpleboilerplates.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding
    abstract fun getViewBinding(): VB

    private var baseActivity: BaseActivity<VB>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity: BaseActivity<VB> = context as BaseActivity<VB>
            baseActivity = activity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
    }

    open fun init(savedInstanceState: Bundle?) {
        configureViews()
        configureViews(savedInstanceState)

    }

    fun navigateTo(
        defaultDestination: NavDirections? = null,
        deepLink: String? = null,
        navOptions: NavOptions? = null
    ) = baseActivity?.navigateTo(defaultDestination, deepLink, navOptions)

    open fun configureViews() {}

    open fun configureViews(savedInstanceState: Bundle?) {}

    open fun getBaseViewModel(): BaseViewModel? = null

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}