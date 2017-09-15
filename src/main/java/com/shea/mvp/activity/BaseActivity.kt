package com.shea.mvp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shea.mvp.presenter.BaseInterface

abstract class BaseActivity<out T : BaseInterface.BasePresenterInterface> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        getPresenter().setupViews(savedInstanceState)
        lifecycle.addObserver(getPresenter())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getPresenter().onSaveState(outState)
    }

    // Override to inject with some DI
    protected open fun injectDependencies() { }
    protected abstract fun getPresenter() : T
    protected abstract val layoutId: Int
}
