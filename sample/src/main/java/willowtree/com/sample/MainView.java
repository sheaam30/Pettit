package willowtree.com.sample;

import android.os.Bundle;

import com.shea.mvp.activity.BaseActivity;
import com.shea.mvp.view.BaseView;

public class MainView extends BaseView<MainPresenterInterface> {

    
    public MainView(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void onSetupViews(Bundle savedInstanceState) {

    }
}
