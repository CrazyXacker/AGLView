package com.hippo.glview.example;

import android.app.Activity;

import androidx.annotation.IdRes;

import com.hippo.glview.view.GLRoot;

public abstract class GLActivity extends Activity {

    private GLRoot mGLRoot;

    @IdRes
    protected abstract int getGLRootViewId();

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (mGLRoot != null) {
            throw new IllegalStateException("Can't set content twice");
        }
        mGLRoot = (GLRoot) findViewById(getGLRootViewId());
        if (mGLRoot == null) {
            throw new IllegalStateException("Can't find GLRootView");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public GLRoot getGLRoot() {
        return mGLRoot;
    }
}
