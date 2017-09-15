package com.shea.mvp.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import com.shea.mvp.interactor.BaseInteractor

/**
 * Created by adamshea on 9/13/17.
 */
interface IBasePresenter<I : BaseInteractor, V : BaseInterface.BaseViewInterface> : BaseInterface.BasePresenterInterface {

    var view: V
        get() = view()
        set(value) = setViewTemplate(value)
    var interactor: I
        get() = interactor()
        set(value) = setInteractorTemplate(value)

    override fun setupViews(savedInstanceState: Bundle?) {
        view.setupViews(savedInstanceState)
        onSetupViews(savedInstanceState)
    }

    fun onSetupViews(savedInstanceState: Bundle?) { /*Override*/
    }


    fun view() : V {
        return view
    }

    fun interactor() : I {
        return interactor
    }

    fun setViewTemplate(view: V) {
        this.view = view
    }

    fun setInteractorTemplate(interactor: I) {
        this.interactor = interactor
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        view.destroy()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        view.attach(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        view.detach()
    }

    override fun onSaveState(outState: Bundle) { /* Override*/
    }
}