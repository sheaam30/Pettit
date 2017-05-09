package willowtree.com.sample;

import com.shea.mvp.interactor.BaseInteractor;

import io.reactivex.Observable;

public class MainInteractor extends BaseInteractor {
    private Model model;

    public MainInteractor() {
        super();
        model = new Model();
    }

    public Observable<String> switchText(String oldText) {
        String newText = model.getNextString();
        model.setText(oldText);
        return Observable.just(newText);
    }
}
