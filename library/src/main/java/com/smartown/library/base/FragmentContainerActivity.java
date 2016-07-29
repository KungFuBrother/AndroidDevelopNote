package com.smartown.library.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartown.library.R;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-28 17:28
 * <p/>
 * 描述：
 */
public class FragmentContainerActivity extends BaseActivity {

    private ImageView backButton;
    private TextView titleTextView;

    private String title;
    private String fragmentClass;
    private Bundle fragmentArgument;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        findViews(R.layout.activity_fragment_container);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("title")) {
                title = intent.getStringExtra("title");
            }
            if (intent.hasExtra("fragmentClass")) {
                fragmentClass = intent.getStringExtra("fragmentClass");
            }
            if (intent.hasExtra("fragmentArgument")) {
                fragmentArgument = intent.getBundleExtra("fragmentArgument");
            }
        }
    }

    @Override
    protected void findViews(@LayoutRes int contentView) {
        setContentView(contentView);

        backButton = (ImageView) findViewById(R.id.container_title_back);
        titleTextView = (TextView) findViewById(R.id.container_title_text);

        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
        titleTextView.setText(title);
        try {
            Fragment fragment = (Fragment) Class.forName(fragmentClass).newInstance();
            if (fragmentArgument != null) {
                fragment.setArguments(fragmentArgument);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void registerViews() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
