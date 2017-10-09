package com.shea.mvp.activity

import android.app.Activity
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.IdRes
import android.view.View
import butterknife.ButterKnife
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

    @CallSuper
    override fun setupViews(bundle: Bundle?) {
        ButterKnife.bind(this, this)
    }

    fun <T : View> Activity.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res)
    }
}
