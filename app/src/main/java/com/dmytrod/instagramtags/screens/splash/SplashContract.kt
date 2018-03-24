package com.dmytrod.instagramtags.screens.splash

import android.net.Uri
import com.dmytrod.instagramtags.screens.BaseContract

/**
 * Created by Dmytro Denysenko on 23.01.18.
 */

interface SplashContract {
    interface View {
        fun navigateToAuth(uri: Uri)
        fun navigateToList()

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun checkToken()
        fun checkIntentData(intentData: Uri)
    }
}
