package com.shea.mvp.presenter

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle
import com.shea.mvp.view.BaseViewInterface

/**
 * Created by adamshea on 10/8/17.
 */
interface BasePresenterInterface<V : BaseViewInterface> : LifecycleObserver {
    var view : V

    fun onSaveState(outState: Bundle)
    fun onViewsSetup()
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//        fun onResume() { }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//        fun onPause() { }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//        fun onDestroy() {
//            view.destroy()
//        }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//        fun onCreate() { }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_START)
//        fun onStart() { }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//        fun onStop() { }

}