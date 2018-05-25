package com.example.whc.alipaytest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.whc.alipaystander.AlipayInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by WSY on 2018/3/6.
 */

public class ProxyActivity extends Activity {

    //要跳转的apk的activity
    private String className;
    private AlipayInterface alipayInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className=getIntent().getStringExtra("className");

        //此处不能用这些方法加载class
//        getClassLoader().loadClass(className);
//        Class.forName(className);

        try {
            Class activityClass=getClassLoader().loadClass(className);
            Constructor constructor=activityClass.getConstructor(new Class[]{});
            Object instance=constructor.newInstance(new Object[]{});
            Log.e("ProxyActivity", "instance: "+String.valueOf(instance == null));
            alipayInterface= (AlipayInterface) instance;
            alipayInterface.attch(ProxyActivity.this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //传递信息
        Bundle bundle=new Bundle();
        alipayInterface.onCreate(bundle);
    }

        @Override
    protected void onStart() {
        super.onStart();
        alipayInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        alipayInterface.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        alipayInterface.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        alipayInterface.onStop();
    }

    @Override
    public void startActivity(Intent intent) {
        String classname=intent.getStringExtra("className");
        Intent intent1=new Intent(this,ProxyActivity.class);
        intent1.putExtra("className",classname);
        super.startActivity(intent1);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }
}
