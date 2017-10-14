package com.shea.mvp.fragment

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shea.mvp.BaseContract
import dagger.android.support.DaggerFragment

abstract class BaseFragment<out P : BaseContract.Presenter> :  DaggerFragment(), BaseContract.View<P> {

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

    fun <T : View> Fragment.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return activity.findViewById(res)
    }
}