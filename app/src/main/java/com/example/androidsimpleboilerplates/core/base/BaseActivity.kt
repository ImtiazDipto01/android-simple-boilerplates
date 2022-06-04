package com.example.androidsimpleboilerplates.core.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.example.androidsimpleboilerplates.R
import timber.log.Timber


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    val binding get() = _binding
    abstract fun getViewBinding(): VB
    private var title: String? = null



    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        _binding = getViewBinding()
        setContentView(binding?.root)
        init(savedInstanceState)
        //lifecycleScope.launch { bindWithViewModel() }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }


    /**
     * Navigates to given destination with [defaultDestination] and [deepLink]
     * [deepLink] will get priority over [defaultDestination]
     * @param defaultDestination is the direction of the destination
     * @param deepLink is the deep link
     * @param navOptions is the [NavOptions] that is expected to run while navigation
     */
    fun navigateTo(
        defaultDestination: NavDirections? = null,
        deepLink: String? = null,
        navOptions: NavOptions? = null
    ) {
        if (defaultDestination == null && deepLink == null) {
            Timber.d("navigateTo: No defaultDestinationId or deepLink provided")
            return
        }
        try {
            val controller = findNavController(R.id.nav_host_fragment)
            if (deepLink != null) {
                processDeepLink(this, controller, Uri.parse(deepLink), navOptions)
                return
            }
            defaultDestination?.let { controller.navigate(it, navOptions) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * This method will help to find app deeplink, if found it will safely navigate there
     * or other wise will try validated the deeplink is valid URL or not,
     * if valid then safely open the URL
     * @param context is context of the current activity
     * @param controller is app navigation controller from nav host.
     * @param deepLink will contain app deepLink or web link.
     */
    private fun processDeepLink(
        context: Context,
        controller: NavController,
        deepLink: Uri,
        navOptions: NavOptions? = null
    ) {
        if (controller.graph.hasDeepLink(deepLink)) {
            controller.navigate(deepLink, navOptions)
        } else if (URLUtil.isValidUrl(deepLink.toString())) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, deepLink)
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    // Handle display size changes
    override fun attachBaseContext(baseContext: Context) {
        val newContext: Context
        val displayMetrics: DisplayMetrics = baseContext.resources.displayMetrics
        val configuration: Configuration = baseContext.resources.configuration
        if (displayMetrics.densityDpi != DisplayMetrics.DENSITY_DEVICE_STABLE) {
            // Current density is different from Default Density. Override it
            configuration.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE
            newContext = baseContext.createConfigurationContext(configuration)
        } else {
            // Same density. Just use same context
            newContext = baseContext
        }
        super.attachBaseContext(newContext)
    }


    abstract fun getActivityName(): String

    open fun configureViews() {}

    open fun getBaseViewModel(): BaseViewModel? = null

    open fun init(savedInstanceState: Bundle?) {
        configureViews()
    }

    /*open fun hideProgress() {
        Timber.d("Progress: Hide")
        try {
            supportFragmentManager.executePendingTransactions()
        } catch (e: Exception) {
            Timber.d("Failed to execute")
        }
        val fragment = supportFragmentManager
            .findFragmentByTag(ProgressDialogFragment.fragmentTag)
        fragment.cast<DialogFragment>()?.dismiss() ?: Timber.d("Failed to hide progress")

    }

    open fun showProgress() {
        Timber.d("Progress: Show")
        var fragment: ProgressDialogFragment? = supportFragmentManager
            .findFragmentByTag(
                ProgressDialogFragment.fragmentTag
            ) as ProgressDialogFragment?
        if (fragment == null) {
            fragment = ProgressDialogFragment.newInstance()
        }
        if (!fragment.isAdded) {
            fragment
                .show(supportFragmentManager, ProgressDialogFragment.fragmentTag)
        }
    }

    fun showError(error: CustomException?) {
        showErrorDialog(error?.errorId, error?.message ?: "")
    }*/


}