package com.shea.mvp.view

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by adamshea on 10/8/17.
 */
interface BaseViewInterface {

    fun destroy() { }

    // Override to inject with some DI
    fun injectDependencies() { }
    val layoutId: Int


    fun <T : View> Activity.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res)
    }

    fun <T : View> Fragment.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return view!!.findViewById(res)
    }
}