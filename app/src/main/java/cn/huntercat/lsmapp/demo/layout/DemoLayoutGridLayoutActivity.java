package cn.huntercat.lsmapp.demo.layout;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;

/**
 * 功能描述：网格布局 Activity
 */
public class DemoLayoutGridLayoutActivity extends AppCompatActivity {

    private static final String TAG = DemoLayoutGridLayoutActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_layout_grid_layout);
    }
}
