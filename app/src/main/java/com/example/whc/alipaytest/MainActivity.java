package com.example.whc.alipaytest;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Environment;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_load,bt_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);
        bt_load=findViewById(R.id.bt_load);
        bt_intent=findViewById(R.id.bt_intent);
        bt_load.setOnClickListener(this);
        bt_intent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bt_load:
                File path=new File(Environment.getExternalStorageDirectory(),"testapk.apk");
                PluginManager.getInstance().loadPath(path.getAbsolutePath());

//                ComponentName componentName=new ComponentName("com.example.whc.testapk",
//                        "com.example.whc.testapk.MainActivity");
//
//                Intent intent= new Intent();
//                //我们给他添加一个参数表示从apk1传过去的
//                Bundle bundle = new Bundle();
//                bundle.putString("arge1", "这是跳转过来的！来自apk1");
//                intent.putExtras(bundle);
//                intent.setComponent(componentName);
//                startActivity(intent);
                break;
            case R.id.bt_intent:
                Intent intent1=new Intent(this,ProxyActivity.class);
                intent1.putExtra("className",PluginManager.getInstance().getEntryActivityName());
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
