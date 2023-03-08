package cn.huntercat.lsmapp.demo.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;

public class DemoWebViewActivity extends AppCompatActivity {

    private final String TAG = DemoWebViewActivity.class.getSimpleName();

    private EditText etDemoWebView;
    private String etDemoWebViewUrl = "https://huntercat.cn";
    private Button btnDemoWebView, btnDemoWebViewGoBack, btnDemoWebViewGoNext;
    private WebView wbDemoWebView;
    private ProgressBar pbDemoWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_webview);
        etDemoWebView = findViewById(R.id.et_demo_webview);
        btnDemoWebView = findViewById(R.id.btn_demo_webview);
        btnDemoWebViewGoBack = findViewById(R.id.btn_demo_webview_go_back);
        btnDemoWebViewGoNext = findViewById(R.id.btn_demo_webview_go_next);
        pbDemoWebView = findViewById(R.id.pb_demo_webview);
        wbDemoWebView = findViewById(R.id.wv_demo_webview);
        // 开启 JS
        wbDemoWebView.getSettings().setJavaScriptEnabled(true);
        // 加载网页大数据
        wbDemoWebView.loadDataWithBaseURL(etDemoWebViewUrl, null, "text/html", "utf-8", null);
        // 对应网址栏
        wbDemoWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    // 开始加载，显示此进度条
                    pbDemoWebView.setVisibility(View.VISIBLE);
                    pbDemoWebView.setProgress(newProgress);
                } else {
                    // 加载完毕隐藏此进度条
                    pbDemoWebView.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        wbDemoWebView.loadUrl(etDemoWebViewUrl);
        btnDemoWebView.setOnClickListener(v -> {
            etDemoWebViewUrl = etDemoWebView.getText().toString();
            Log.i(TAG, "路径为:" + etDemoWebViewUrl);
            wbDemoWebView.loadUrl(etDemoWebViewUrl);
        });
        btnDemoWebViewGoBack.setOnClickListener(v -> wbDemoWebView.goBack());
    }
}
