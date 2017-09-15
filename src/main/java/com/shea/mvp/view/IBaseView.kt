package com.shea.mvp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.ButterKnife
import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.presenter.BaseInterface
import kotlin.reflect.KProperty

/**
 * Created by adamshea on 9/11/17.
 */
interface IBaseView<P : BaseInterface.BasePresenterInterface> {

    var baseActivity: BaseActivity<P>?
        get() = getActivity()
        set(value) = setActivity(value as BaseActivity<P>)

    var presenterInterface: P?
        get() = getPresenter()
        set(value) = attach(value!!)

    fun destroy() {
        baseActivity = null
    }

    fun getActivity() : BaseActivity<P>? {
        return baseActivity
    }

    fun setActivity(activity: BaseActivity<P>) {
        this.baseActivity = activity
    }

    fun getPresenter() : P? {
        return presenterInterface
    }

    fun attach(presenter: P) {
        this.presenterInterface = presenter
    }

    fun detach() {
        this.presenterInterface = null
    }

    fun <T : View> bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return baseActivity!!.bind(res)
    }


    fun setupViews(savedInstanceState: Bundle?) {
        ButterKnife.bind(this, baseActivity!!)
        onSetupViews(savedInstanceState)
    }

    private fun viewNotFound(id:Int, desc: KProperty<*>): Nothing =
            throw IllegalStateException("View ID $id for '${desc.name}' not found.")


    fun setSupportActionbar(toolbar: Toolbar) {
        baseActivity?.setSupportActionBar(toolbar)
    }

    /**
     * Provide subclass with a AlertDialog Builder
     * so they can create their own AlertDialog
     */
    val dialogBuilder: AlertDialog.Builder
        get() = AlertDialog.Builder(baseActivity!!)

    val resources: Resources
        get() = baseActivity!!.resources

    val context: Context
        get() = baseActivity!!

    fun sendIntent(intent : Intent) {
        baseActivity?.startActivity(intent)
    }

    abstract fun onSetupViews(savedInstanceState: Bundle?)

    fun <T : View> Activity.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res)
    }

    fun getFragmentManager() : FragmentManager {
        return baseActivity!!.supportFragmentManager
    }

}