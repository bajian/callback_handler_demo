package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NetWork.DosthCallBack {

    private NetWork mNetWork=new NetWork();
    private MyHandler mMyHandler=new MyHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册

        mNetWork.setDosthCallBackListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1) {//1是来的消息类型，可以自定义
                //属于主线程可以直接更新ui，当然可以通过msg.obj传数据
                Toast.makeText(MainActivity.this,"我收到子线程来的消息了"+msg.obj,Toast.LENGTH_LONG).show();
            }
        }
    }

    public void callback(View view){
        Log.d("callback","begin");
        mNetWork.dosth();
        Log.d("callback","end");
    }

    public void handler(View view){
        Log.d("handler","begin");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = mMyHandler.obtainMessage();
                message.what = 1;
                message.obj="这是我传来的值233333";//传值可以是对象之类的
                mMyHandler.sendMessage(message);
            }
        }).start();
        Log.d("hanlder","end");
    }

    @Override
    public void succ(String str) {
        //实现接口的方法回调过来就触发
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
        Log.d("succ", str);
    }

    @Override
    public void fail(String reason) {
        Toast.makeText(this,reason,Toast.LENGTH_LONG).show();
        Log.d("fail", reason);
    }
}
