package cn.huntercat.lsmapp.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 功能描述：第一个简单的 Service 服务
 */
public class MyService extends Service {

    public static final String TAG = "MyService";

    private Thread serviceMainThread;
    private Boolean serviceMainThreadFlag = true; // 服务主线程的停止标记

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 此处一般是启动 服务主要逻辑的地方
        int result = super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "onStartCommand");
        // 主要逻辑
        main();
        return result;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 通过线程内操作，来停止线程逻辑
        serviceMainThreadFlag = false;
        Log.i(TAG, "onDestroy");
    }

    private void main() {
        // 只允许有一个线程执行
        if (serviceMainThread == null) {
            serviceMainThread = new Thread(() -> {
                String msg = "当前进程号（" + Process.myPid() + ")当前线程号（" + Process.myTid() + ")当前用户号(" + Process.myUid() + ")";
                Log.i(TAG, "子线程逻辑开始:" + msg);
                int i = 0;
                while (serviceMainThreadFlag) {
                    try {
                        Log.i(TAG, "我的主要逻辑循环:" + i + msg);
                        Thread.sleep(2000);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.i(TAG, "子线程逻辑结束:" + msg);
            });
            serviceMainThread.start();
        } else {
            Log.e(TAG, "只允许有一个线程执行");
        }
    }

    public class MyBinder extends Binder {
        public void myMethod() {
            main();
        }
    }


}
