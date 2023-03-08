package cn.huntercat.lsmapp.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;

public class DemoBroadcastActivity extends AppCompatActivity {

    private static final String STANDARD_ACTION = "cn.huntercat.lsmapp.demo.broadcast.DemoBroadcastActivity";

    private TextView tvDemoBroadcastContent;

    private MyReceiver myReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(STANDARD_ACTION);
        registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_broadcast);
        tvDemoBroadcastContent = findViewById(R.id.tv_demo_broadcast_content);
        findViewById(R.id.btn_demo_broadcast_send).setOnClickListener(v -> {
            Intent intent = new Intent(STANDARD_ACTION); // 创建指定动作的意图
            sendBroadcast(intent); // 发送标准广播
        });
    }

    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(STANDARD_ACTION)) {
                tvDemoBroadcastContent.setText("这里查看广播信息");
            }
        }
    }
}
