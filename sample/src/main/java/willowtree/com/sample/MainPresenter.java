package willowtree.com.sample;

import com.shea.mvp.presenter.BasePresenter;
import com.shea.mvp.presenter.BasePresenterInterface;

public class MainPresenter extends BasePresenter<MainInteractor, MainView> implements MainPresenterInterface {

    MainPresenter(MainInteractor interactor, MainView view) {
        super(interactor, view);
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
    }
}

interface MainPresenterInterface extends BasePresenterInterface {

}