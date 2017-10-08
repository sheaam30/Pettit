package com.shea.mvp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shea.mvp.presenter.BaseInterface

interface BaseActivity<out T : BaseInterface.BasePresenterInterface> {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        lifecycle.addObserver(getPresenter())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getPresenter().onSaveState(outState)
    }

    // Override to inject with some DI
    fun injectDependencies() { }
    fun getPresenter() : T
    val layoutId: Int
}
