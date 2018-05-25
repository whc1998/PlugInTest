package com.example.whc.testapk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.whc.alipaystander.AlipayInterface;

/**
 * Created by WSY on 2018/3/30.
 */

public class NewBaseActivity extends Activity implements AlipayInterface {


    protected Activity that;

    /**
     * super.setContentView(view);
     * 最终是调用了系统注入的上下文
     *
     * @param view
     */
    @Override
    public void setContentView(View view) {
        if (that == null) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }

    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (that == null) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (that == null) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        if (that==null){
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (that==null){
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (that==null){
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (that==null){
            super.onStop();
        }

    }

    @Override
    public void onDestroy() {
        if (that==null){
            super.onDestroy();
        }
    }


    @Override
    public void onBackPressed() {

    }

    @Override
    public void attch(Activity activity) {
        this.that = activity;
    }

    @Override
    public void startActivity(Intent intent) {
        Intent newintent=new Intent();
        newintent.putExtra("className",intent.getComponent().getClassName());
        that.startActivity(newintent);
    }
}
