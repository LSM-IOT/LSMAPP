package cn.huntercat.lsmapp.demo.widget;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;
import cn.huntercat.lsmapp.demo.layout.DemoLayoutRelativeLayoutActivity;

public class DemoWidgetActivity extends AppCompatActivity {

    private static final String TAG = DemoLayoutRelativeLayoutActivity.class.getSimpleName();

    private TextView tvHeadAdwidget, tvContentAdwidget;

    private String strHeadAdwidget = "基础控件";
    private String strContentAdwidget = "基础控件：TextView，EditView，Button";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_widget);

        tvHeadAdwidget = findViewById(R.id.tv_header_adwidget);
        tvContentAdwidget = findViewById(R.id.tv_content_adwidget);

        tvHeadAdwidget.setText(strHeadAdwidget);
        tvContentAdwidget.setText(strContentAdwidget);

    }
}
