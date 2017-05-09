package com.shea.mvp.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import com.shea.mvp.activity.BaseActivity;
import com.shea.mvp.presenter.BasePresenterInterface;

import butterknife.ButterKnife;


abstract public class BaseView<T extends BasePresenterInterface> {

    //Lock down this Activity so nobody does anything mischievous with it.
    private BaseActivity activity;
    protected T presenter;

    public BaseView(BaseActivity activity) {
        this.activity = activity;
    }

    public void attach(T presenter) {
        this.presenter = presenter;
    }

    public void detach() {
        this.presenter = null;
    }

    public final void setupViews(Bundle savedInstanceState) {
        ButterKnife.bind(this, activity);
        onSetupViews(savedInstanceState);
    }

    public final void destroy() {
        activity = null;
    }

    protected final void setSupportActionbar(Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
    }

    /**
     * Provide subclass with a AlertDialog Builder
     * so they can create their own AlertDialog
     */
    protected final AlertDialog.Builder getDialogBuilder() {
        return new AlertDialog.Builder(activity);
    }

    protected final Resources getResources() {
        return activity.getResources();
    }

    abstract public void onSetupViews(Bundle savedInstanceState);
}
