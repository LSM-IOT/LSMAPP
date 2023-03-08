package cn.huntercat.lsmapp.demo.service;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.huntercat.lsmapp.R;

/**
 * 功能描述：Service 案例测试
 */
public class DemoServiceActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private Boolean activityMainThreadFlag = true; // Activity 主线程的停止标记
    private MyService.MyBinder myBinder;
    private ServiceConnection myServiceConnection;

    private Button
            btnStartService,
            btnStopService,
            btnFindService,
            btnBindService,
            btnUnBindService,
            btnRunBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_service);
        initView();
        initService();
        // main();
    }

    private void initService() {
        myServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (MyService.MyBinder) service;
                Log.i(TAG, "onServiceConnected:" + name);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected:" + name);
            }
        };
    }

    private void initView() {
        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        btnFindService = findViewById(R.id.btn_find_service);
        btnBindService = findViewById(R.id.btn_bind_service);
        btnUnBindService = findViewById(R.id.btn_unbind_service);
        btnRunBindService = findViewById(R.id.btn_run_bind_service);
        btnStartService.setOnClickListener(v -> clickBtnStartService());
        btnStopService.setOnClickListener(v -> clickBtnStopService());
        btnFindService.setOnClickListener(v -> clickBtnFindService());
        btnBindService.setOnClickListener(v -> clickBtnBindService());
        btnUnBindService.setOnClickListener(v -> clickBtnUnBindService());
        btnRunBindService.setOnClickListener(v -> clickBtnRunBindService());
    }

    /**
     * 功能描述：开启服务按钮
     */
    private void clickBtnStartService() {
        Log.i(TAG, "clickBtnStartService");
        startService(new Intent(this, MyService.class));
    }

    /**
     * 功能描述：关闭服务按钮
     */
    private void clickBtnStopService() {
        Log.i(TAG, "clickBtnStopService");
        stopService(new Intent(this, MyService.class));
    }

    /**
     * 功能描述：查询服务按钮
     */
    private void clickBtnFindService() {
        Log.i(TAG, "clickBtnFindService");
        // 获取 Activity 管理器
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        // 获取当前窗口中运行的所有 Service
        List<ActivityManager.RunningServiceInfo> serviceInfoList = activityManager.getRunningServices(30);
        for (ActivityManager.RunningServiceInfo rsi : serviceInfoList) {
            Log.i(TAG, rsi.service.getClassName());
        }
    }

    /**
     * 功能描述：绑定服务按钮
     */
    private void clickBtnBindService() {
        Log.i(TAG, "clickBtnBindService");
        bindService(new Intent(DemoServiceActivity.this, MyService.class), myServiceConnection, BIND_AUTO_CREATE);
    }

    /**
     * 功能描述：解除绑定服务按钮
     */
    private void clickBtnUnBindService() {
        Log.i(TAG, "clickBtnUnBindService");

        try {
            unbindService(myServiceConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 功能描述：运行绑定的服务
     */
    private void clickBtnRunBindService() {
        myBinder.myMethod();
    }

    /**
     * 活动主线程
     */
    private void main() {
        String msg = "当前进程号（" + Process.myPid() + ")当前线程号（" + Process.myTid() + ")当前用户号(" + Process.myUid() + ")";
        Log.i(TAG, "UI线程逻辑开始:" + msg);
        int i = 0;
        while (activityMainThreadFlag) {
            try {
                Log.i(TAG, "我的主要逻辑循环:" + i + msg);
                Thread.sleep(5000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "子线程逻辑结束:" + msg);
    }
}