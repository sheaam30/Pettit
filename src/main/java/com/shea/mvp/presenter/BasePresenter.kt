package com.shea.mvp.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import com.shea.mvp.interactor.BaseInteractor


abstract class BasePresenter<I : BaseInteractor, V : BaseInterface.BaseViewInterface>(protected var interactor: I, protected var view: V) : BaseInterface.BasePresenterInterface {

    override fun setupViews(savedInstanceState: Bundle?) {
        view.setupViews(savedInstanceState)
        onSetupViews(savedInstanceState)
    }

    open fun onSetupViews(savedInstanceState: Bundle?) { /*Override*/
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        view.destroy()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    override fun onSaveState(outState: Bundle) { /* Override*/
    }
}