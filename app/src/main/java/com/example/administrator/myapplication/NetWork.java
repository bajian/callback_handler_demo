package com.example.administrator.myapplication;

import android.os.Handler;

/**
 * Created by jz on 2015/7/7.
 */
public class NetWork {
    private DosthCallBack mCallBack;

    public void setDosthCallBackListener(DosthCallBack callBack){//需要注册这个接口
        mCallBack=callBack;
    }


    public void dosth(){
        //比如网络请求之类的延迟，模拟下请求成功和失败的情况
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //模拟随机触发事件
                if (mCallBack!=null){
                    if (((int)(Math.random()*10)%2)==0)
                        mCallBack.succ("成功啦，这是结果");

                    mCallBack.fail("失败啦，这是结果");
                }else{
                    try {
                        throw new Exception("mCallBack为注册");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        },3000);//模拟做某事延迟
    }

    //声明一个接口
    public interface DosthCallBack {
        void succ(String str);
        void fail(String reason);
    }
}
