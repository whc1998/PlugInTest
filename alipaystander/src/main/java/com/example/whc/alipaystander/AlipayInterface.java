package com.example.whc.alipaystander;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by WSY on 2018/3/6.
 */

public interface AlipayInterface {

    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

   public void onDestroy();

    public void onSaveInstanceState(Bundle outState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackPressed();

    //需要支付宝注入上下文
    public void attch(Activity activity);

}
