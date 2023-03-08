package cn.huntercat.lsmapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.demo.andserver.DemoAndServerActivity;
import cn.huntercat.lsmapp.demo.broadcast.DemoBroadcastActivity;
import cn.huntercat.lsmapp.demo.file.DemoFileActivity;
import cn.huntercat.lsmapp.demo.layout.DemoLayoutIndexActivity;
import cn.huntercat.lsmapp.demo.service.DemoServiceActivity;
import cn.huntercat.lsmapp.demo.webview.DemoWebViewActivity;
import cn.huntercat.lsmapp.demo.widget.DemoWidgetActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Demo 测试 Service
        Button btnDemoLayoutIndex = findViewById(R.id.btn_demo_layout_index);
        btnDemoLayoutIndex.setOnClickListener(v -> startActivity(new Intent(this, DemoLayoutIndexActivity.class)));
        // Demo 测试 Widget
        Button btnDemoWidget = findViewById(R.id.btn_demo_base_widget);
        btnDemoWidget.setOnClickListener(v -> startActivity(new Intent(this, DemoWidgetActivity.class)));
        // Demo 测试 Service
        Button btnDemoService = findViewById(R.id.btn_demo_service);
        btnDemoService.setOnClickListener(v -> startActivity(new Intent(this, DemoServiceActivity.class)));
        // Demo 测试 WebView
        Button btnWebViewService = findViewById(R.id.btn_demo_webview);
        btnWebViewService.setOnClickListener(v -> startActivity(new Intent(this, DemoWebViewActivity.class)));
        // Demo 测试 file
        Button btnFile = findViewById(R.id.btn_demo_file);
        btnFile.setOnClickListener(v -> startActivity(new Intent(this, DemoFileActivity.class)));
        // Demo 测试 AndServer
        Button btnAndServer = findViewById(R.id.btn_demo_andserver);
        btnAndServer.setOnClickListener(v -> startActivity(new Intent(this, DemoAndServerActivity.class)));
        // Demo 测试 Broadcast
        Button btnBroadcast = findViewById(R.id.btn_demo_broadcast);
        btnBroadcast.setOnClickListener(v -> startActivity(new Intent(this, DemoBroadcastActivity.class)));

    }
}