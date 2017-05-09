package willowtree.com.sample;

import android.os.Bundle;

import com.shea.mvp.activity.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> {

    @Override
    protected void createPresenter(Bundle restoredBundle) {
        presenter = new MainPresenter(new MainInteractor(), new MainView(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
