package com.shea.mvp.view

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.ButterKnife
import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.presenter.BaseInterface
import kotlin.reflect.KProperty

abstract class BaseView<T : BaseInterface.BasePresenterInterface> (
        open var activity: BaseActivity<*>?) : BaseInterface.BaseViewInterface {

    protected var presenterInterface: T? = null

    override fun destroy() {
        activity = null
    }

    override fun attach(presenter: BaseInterface.BasePresenterInterface) {
        presenterInterface = presenter as? T
    }

    override fun detach() {
        this.presenterInterface = null
    }

    fun <T : View> bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return activity!!.bind(res)
    }

    override fun setupViews(bundle: Bundle?) {
        ButterKnife.bind(this, activity!!)
        onSetupViews(bundle)
    }

    private fun viewNotFound(id:Int, desc: KProperty<*>): Nothing =
            throw IllegalStateException("View ID $id for '${desc.name}' not found.")

    protected fun setSupportActionbar(toolbar: Toolbar) {
        activity?.setSupportActionBar(toolbar)
    }

    /**
     * Provide subclass with a AlertDialog Builder
     * so they can create their own AlertDialog
     */
    protected val dialogBuilder: AlertDialog.Builder
        get() = AlertDialog.Builder(activity!!)

    protected val resources: Resources
        get() = activity!!.resources

    fun sendIntent(intent : Intent) {
        activity?.startActivity(intent)
    }

    abstract fun onSetupViews(savedInstanceState: Bundle?)

    fun <T : View> Activity.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res)
    }
}
