package com.example.supervideo;

import com.example.supervideo.base.BaseFragment;

import java.util.HashMap;

/**
 * 目的是为了创建Fragment
 */
public class FragmentManagerWrapper {
    private volatile static FragmentManagerWrapper mInstance = null;

    private FragmentManagerWrapper() {
    }

    ;

    public static FragmentManagerWrapper getmInstance() {
        if (mInstance == null) {
            synchronized (FragmentManagerWrapper.class) {
                if (mInstance == null) {
                    mInstance = new FragmentManagerWrapper();
                }
            }
        }
        return mInstance;
    }

    //存储Fragment的变量
    private HashMap<String, BaseFragment> mHashMap = new HashMap<>();

    public BaseFragment createFragment(Class<?> clazz){
        return createFragment(clazz,true);
    }
    //根据类名，并且判断该类名是hashMap的key值，
    public BaseFragment createFragment(Class<?> clazz, boolean isobtain) {
        BaseFragment resultFragment = null;
        String className = clazz.getName();
        //如果包含则直接取出，否则自己创建实例
        if (mHashMap.containsKey(className)) {
            resultFragment=mHashMap.get(className);
        }else{
            try {
                resultFragment = (BaseFragment) Class.forName(className).newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //true是第一次创建,则要存入hashmap
        if (isobtain) {
            mHashMap.put(className, resultFragment);
        }
        return resultFragment;
    }

}
