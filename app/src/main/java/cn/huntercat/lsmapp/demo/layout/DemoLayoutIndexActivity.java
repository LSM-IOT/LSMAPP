package cn.huntercat.lsmapp.demo.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;

public class DemoLayoutIndexActivity extends AppCompatActivity {

    private static final String TAG = DemoLayoutIndexActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_layout_index);

        Button btnLayoutLinearLayout = findViewById(R.id.btn_layout_linearlayout);
        btnLayoutLinearLayout.setOnClickListener(v -> startActivity(
                new Intent(this, DemoLayoutLinearLayoutActivity.class)));
        Button btnLayoutRelativeLayout = findViewById(R.id.btn_layout_relativelayout);
        btnLayoutRelativeLayout.setOnClickListener(v -> startActivity(
                new Intent(this, DemoLayoutRelativeLayoutActivity.class)));

    }
}
