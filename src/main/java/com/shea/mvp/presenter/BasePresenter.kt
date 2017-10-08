package com.shea.mvp.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import com.shea.mvp.repository.BaseRepository


abstract class BasePresenter<I : BaseRepository, V : BaseInterface.BaseViewInterface>(protected var interactor: I, protected var view: V) : BaseInterface.BasePresenterInterface {
    /**
     * This method is called after the View's views are set up, meaning
     * it's safe to reference them.
     */
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
        view.attach(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        view.detach()
    }

    /**
     * Override this to save instance state
     */
    override fun onSaveState(outState: Bundle) { /* Override*/
    }
}