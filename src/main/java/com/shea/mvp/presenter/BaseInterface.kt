package com.shea.mvp.presenter

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle

/**
 * Created by adamshea on 5/8/17.
 */

interface BaseInterface {

    interface BasePresenterInterface : LifecycleObserver {
        fun setupViews(savedInstanceState: Bundle?) {
        }

        fun onSaveState(outState: Bundle) {
        }
    }

    interface BaseViewInterface {
        open fun setupViews(bundle: Bundle?)
        open fun destroy()
    }
}
