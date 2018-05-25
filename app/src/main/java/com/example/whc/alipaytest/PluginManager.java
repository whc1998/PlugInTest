package com.example.whc.alipaytest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by WSY on 2018/3/12.
 */

public class PluginManager {

    private DexClassLoader dexClassLoader;
    private Resources resources;
    private Context context;
    private String entryActivityName;

    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }

    public void loadPath(String path){
        //在应用程序的数据文件下获取或创建name对应的子目录。
        File dexOutFile=context.getDir("dex",Context.MODE_PRIVATE);
        dexClassLoader=new DexClassLoader(path,dexOutFile.getAbsolutePath(),null,context.getClassLoader());
        //实例化resource
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        entryActivityName=packageInfo.activities[0].name;

//        try {
//            ///storage/emulated/testapk.apk
//            Class<?> aClass = dexClassLoader.loadClass(entryActivityName);
//            Log.d("classname",aClass.getName());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        try {
            AssetManager assetManager=AssetManager.class.newInstance();
            Method addAssetPath=AssetManager.class.getMethod("addAssetPath",String.class);
            addAssetPath.invoke(assetManager,path);
            resources=new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public String getEntryActivityName() {
        return entryActivityName;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }
}
