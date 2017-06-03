package com.shea.mvp.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar

import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.presenter.BasePresenterInterface

import butterknife.ButterKnife


abstract class BaseView<T : BasePresenterInterface>(//Lock down this Activity so nobody does anything mischievous with it.
        private var activity: BaseActivity<*>?) {

    protected var presenter: T? = null

    fun destroy() {
        activity = null
    }

    fun attach(presenter: T) {
        this.presenter = presenter
    }

    fun detach() {
        this.presenter = null
    }

    fun setupViews(savedInstanceState: Bundle) {
        ButterKnife.bind(this, activity!!)
        onSetupViews(savedInstanceState)
    }

    protected fun setSupportActionbar(toolbar: Toolbar) {
        activity!!.setSupportActionBar(toolbar)
    }

    /**
     * Provide subclass with a AlertDialog Builder
     * so they can create their own AlertDialog
     */
    protected val dialogBuilder: AlertDialog.Builder
        get() = AlertDialog.Builder(activity!!)

    protected val resources: Resources
        get() = activity!!.resources

    abstract fun onSetupViews(savedInstanceState: Bundle)
}
