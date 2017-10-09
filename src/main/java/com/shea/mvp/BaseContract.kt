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

    interface View<out P : BaseContract.Presenter> {
        fun setupViews(bundle: Bundle?)
        fun getPresenter() : P
        val layoutId: Int
    }

    interface Repository { }
}
