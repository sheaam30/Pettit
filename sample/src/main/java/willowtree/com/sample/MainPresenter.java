package willowtree.com.sample;

import android.os.Bundle;

import com.shea.mvp.presenter.BasePresenter;
import com.shea.mvp.presenter.BasePresenterInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainInteractor, MainView> implements MainPresenterInterface {

    CompositeDisposable compositeDisposable;

    MainPresenter(MainInteractor interactor, MainView view) {
        super(interactor, view);
    }

    @Override
    protected void onSetupViews(Bundle savedInstanceState) {
        super.onSetupViews(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.attach(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.detach();
        compositeDisposable.dispose();
    }

    @Override
    public void buttonClicked(String currentText) {
        compositeDisposable.add(interactor.switchText(currentText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        view.updateText(s);
                    }
                }));
    }
}

interface MainPresenterInterface extends BasePresenterInterface {
    void buttonClicked(String currentText);
}