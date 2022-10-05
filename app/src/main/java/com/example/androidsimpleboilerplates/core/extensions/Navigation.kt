package com.example.androidsimpleboilerplates.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.androidsimpleboilerplates.R
import timber.log.Timber

/**
 * Navigates to given destination with [defaultDestination] and [deepLink]
 * [deepLink] will get priority over [defaultDestination]
 * @param defaultDestination is the direction of the destination
 * @param deepLink is the deep link
 * @param navOptions is the [NavOptions] that is expected to run while navigation
 */
fun Activity.navigateTo(
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