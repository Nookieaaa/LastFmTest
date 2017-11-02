package com.nookdev.lastfmtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nookdev.lastfmtest.base.mvp.IPresenter;
import com.nookdev.lastfmtest.base.mvp.IView;

import static com.nookdev.lastfmtest.util.Checks.checkNotNull;


public abstract class BaseActivity extends AppCompatActivity {
    /**
     * Provide layout
     */
    protected abstract View specifyLayout();

    /**
     * Build component and inject here
     */
    protected abstract void performInjection();

    /**
     * Provide view here
     */
    protected abstract IView specifyView();

    /**
     * Provide presenter here
     */
    protected abstract IPresenter specifyPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performInjection();
        setContentView(specifyLayout());
        IPresenter presenter = specifyPresenter();
        IView view = specifyView();
        checkNotNull(presenter);
        checkNotNull(view);

        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        specifyPresenter().onDestroy();
    }
}
