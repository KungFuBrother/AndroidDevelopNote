package com.smartown.note.jos.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smartown.library.base.BaseFragment;
import com.smartown.note.jos.R;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-09-28 14:16
 * <p/>
 * 描述：
 */
public class LoginFragment extends BaseFragment implements LoginView, View.OnClickListener {

    private EditText accountEditText;
    private EditText pwdEditText;
    private Button loginButton;
    private LoginPresenter presenter;

    @Override
    protected void init() {
        presenter = new LoginPresenter(this);
        findViews(R.layout.fragment_user_login);
    }

    @Override
    protected void findViews() {
        accountEditText = (EditText) findViewById(R.id.user_login_account);
        pwdEditText = (EditText) findViewById(R.id.user_login_pwd);
        loginButton = (Button) findViewById(R.id.user_login_login);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerViews() {
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_login_login:
                presenter.login(accountEditText.getText().toString(), pwdEditText.getText().toString());
                break;
        }
    }

}
