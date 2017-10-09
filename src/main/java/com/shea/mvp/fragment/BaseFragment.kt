package com.shea.mvp.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.shea.mvp.BaseContract
import dagger.android.support.DaggerFragment
import kotlin.reflect.KProperty

abstract class BaseFragment<out T : BaseContract.Presenter> :  DaggerFragment(), BaseContract.View {

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

    override fun onAttach(context: Context?) {
        injectDependencies()
        super.onAttach(context)
    }

    override fun setupViews(bundle: Bundle?) {
        ButterKnife.bind(this, activity!!)
        onSetupViews(bundle)
    }

    private fun viewNotFound(id:Int, desc: KProperty<*>): Nothing =
            throw IllegalStateException("View ID $id for '${desc.name}' not found.")

    abstract fun onSetupViews(savedInstanceState: Bundle?)

    fun <T : View> Activity.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res)
    }

    fun <T : View> Fragment.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return activity.findViewById(res)
    }

    // Override to inject with some DI
    protected open fun injectDependencies() { }
    protected abstract fun getPresenter() : T
    protected abstract val layoutId: Int
}