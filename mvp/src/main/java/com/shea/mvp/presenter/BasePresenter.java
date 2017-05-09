package com.shea.mvp.presenter;

import android.os.Bundle;

import com.shea.mvp.interactor.BaseInteractor;
import com.shea.mvp.view.BaseView;


public abstract class BasePresenter<I extends BaseInteractor, V extends BaseView> {
    protected I interactor;
    protected V view;

    public BasePresenter(I interactor, V view) {
        this.interactor = interactor;
        this.view = view;
    }

    public final void setupViews(Bundle savedInstanceState) {
        view.setupViews(savedInstanceState);
        onSetupViews(savedInstanceState);
    }

    public final void resume() {
        onResume();
    }

    public final void pause() {
        onPause();
    }

    public final void destroy() {
        view.destroy();
    }

    protected void onResume() { /*Override*/ }
    protected void onPause() { /*Override*/ }
    protected void onSetupViews(Bundle savedInstanceState) { /*Override*/ }
    public void onSaveState(Bundle outState) { /* Override*/ }
}