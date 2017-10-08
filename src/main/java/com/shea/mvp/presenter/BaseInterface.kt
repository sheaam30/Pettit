package com.shea.mvp.presenter

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle

/**
 * Created by adamshea on 5/8/17.
 */

interface BaseInterface {

    interface BasePresenterInterface : LifecycleObserver {
        fun onSaveState(outState: Bundle)
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() { }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() { }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            view.destroy()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() { }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            view.attach(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            view.detach()
        }
    }

    interface BaseViewInterface {

        fun setupViews(bundle: Bundle?) { }
        fun destroy() { }

        @Suppress("UNCHECKED_CAST")
        fun attach(presenter: BaseInterface.BasePresenterInterface)
        fun detach()
    }
}
