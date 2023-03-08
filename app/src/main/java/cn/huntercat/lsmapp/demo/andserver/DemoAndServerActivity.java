package cn.huntercat.lsmapp.demo.andserver;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;

public class DemoAndServerActivity extends AppCompatActivity {

    private static String TAG = DemoAndServerActivity.class.getSimpleName();

    private DemoAndServerServiceManager demoAndServerServiceManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_andserver);
        Log.i(TAG, "onCreate");

        demoAndServerServiceManager = new DemoAndServerServiceManager(this);
        demoAndServerServiceManager.register();
        demoAndServerServiceManager.startServer();

    }


    public void onServerStart(String ip) {
        Log.i(TAG, "onServerStart:ip" + ip);
    }

    public void onServerError(String message) {
        Log.i(TAG, "onServerError:message" + message);
    }

    public void onServerStop() {
        Log.i(TAG, "onServerStop");
    }

}
