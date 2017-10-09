package com.shea.mvp.activity

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import butterknife.ButterKnife
import com.shea.mvp.BaseContract
import dagger.android.support.DaggerAppCompatActivity
import kotlin.reflect.KProperty

abstract class BaseActivity<out T : BaseContract.Presenter> : DaggerAppCompatActivity() {

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

    fun setupViews(bundle: Bundle?) {
        ButterKnife.bind(this, this)
        onSetupViews(bundle)
    }

    private fun viewNotFound(id:Int, desc: KProperty<*>): Nothing =
            throw IllegalStateException("View ID $id for '${desc.name}' not found.")

    abstract fun onSetupViews(savedInstanceState: Bundle?)

    fun <T : View> Activity.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res)
    }

    // Override to inject with some DI
    protected open fun injectDependencies() { }
    protected abstract fun getPresenter() : T
    protected abstract val layoutId: Int

}
