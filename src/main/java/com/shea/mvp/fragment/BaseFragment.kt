package com.shea.mvp.fragment

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shea.mvp.presenter.BaseInterface

abstract class BaseFragment<out T : BaseInterface.BasePresenterInterface> :  LifecycleFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPresenter().setupViews(savedInstanceState)
        lifecycle.addObserver(getPresenter())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getPresenter().onSaveState(outState)
    }

    protected abstract fun getPresenter() : T
    protected abstract val layoutId: Int
}