package cn.huntercat.lsmapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.demo.andserver.DemoAndServerActivity;
import cn.huntercat.lsmapp.demo.broadcast.DemoBroadcastActivity;
import cn.huntercat.lsmapp.demo.customview.DemoCustomViewActivity;
import cn.huntercat.lsmapp.demo.dialog.DemoDialogActivity;
import cn.huntercat.lsmapp.demo.file.DemoFileActivity;
import cn.huntercat.lsmapp.demo.fragment.DemoFragmentActivity;
import cn.huntercat.lsmapp.demo.layout.DemoLayoutIndexActivity;
import cn.huntercat.lsmapp.demo.network.DemoNetworkActivity;
import cn.huntercat.lsmapp.demo.service.DemoServiceActivity;
import cn.huntercat.lsmapp.demo.viewpager.DemoViewPagerActivity;
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
        // Demo 测试 broadcast
        Button btnDialog = findViewById(R.id.btn_demo_dialog);
        btnDialog.setOnClickListener(v -> startActivity(new Intent(this, DemoDialogActivity.class)));
        // Demo 测试 fragment
        Button btnFragment = findViewById(R.id.btn_demo_fragment);
        btnFragment.setOnClickListener(v -> startActivity(new Intent(this, DemoFragmentActivity.class)));
        // Demo 测试 view pager
        Button btnViewpager = findViewById(R.id.btn_demo_viewpager);
        btnViewpager.setOnClickListener(v -> startActivity(new Intent(this, DemoViewPagerActivity.class)));
        // Demo 测试 view pager
        Button btnNetwork = findViewById(R.id.btn_demo_network);
        btnNetwork.setOnClickListener(v -> startActivity(new Intent(this, DemoNetworkActivity.class)));
        // Demo 测试 自定义View
        Button btnCustomView = findViewById(R.id.btn_demo_custom_view);
        btnCustomView.setOnClickListener(v -> startActivity(new Intent(this, DemoCustomViewActivity.class)));
    }

    /**
     * 创建 Option Menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 将菜单资源，加载进来
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }

    /**
     * 处理菜单点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_option_save:
                Toast.makeText(this, "点击了保存按钮", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}