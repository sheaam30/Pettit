package willowtree.com.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shea.mvp.activity.BaseActivity;
import com.shea.mvp.view.BaseView;

import butterknife.BindView;

public class MainView extends BaseView<MainPresenterInterface> {

    @BindView(R.id.text) TextView textView;
    @BindView(R.id.button) Button button;

    public MainView(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void onSetupViews(Bundle savedInstanceState) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.buttonClicked((String) textView.getText());
            }
        });
    }

    public void updateText(String text) {
        textView.setText(text);
    }
}
