package cn.huntercat.lsmapp.demo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;

public class DemoFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment);

        findViewById(R.id.btn_demo_fragment_dynamic).setOnClickListener(
                v -> startActivity(new Intent(this, DemoFragmentDynamicActivity.class))
        );
        findViewById(R.id.btn_demo_fragment_static).setOnClickListener(
                v -> startActivity(new Intent(this, DemoFragmentStaticActivity.class))
        );

    }
}
