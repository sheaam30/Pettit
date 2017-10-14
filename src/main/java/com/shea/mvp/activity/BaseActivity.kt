package com.shea.mvp.activity

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import com.shea.mvp.BaseContract
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<out P : BaseContract.Presenter> : DaggerAppCompatActivity(), BaseContract.View<P> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        getPresenter().setupViews(savedInstanceState)
        lifecycle.addObserver(getPresenter())
    }

    override final fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getPresenter().onSaveState(outState)
    }

    fun <T : View> Activity.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res)
    }
}
