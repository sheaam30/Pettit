package com.shea.mvp

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle

/**
 * Created by adamshea on 5/8/17.
 */

interface BaseContract {

    interface Presenter : LifecycleObserver {
        fun setupViews(savedInstanceState: Bundle?)
        fun onSaveState(outState: Bundle)
    }

    interface View {
        fun setupViews(bundle: Bundle?)
    }

    interface Repository { }
}
