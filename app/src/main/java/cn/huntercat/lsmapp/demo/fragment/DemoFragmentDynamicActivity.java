package cn.huntercat.lsmapp.demo.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;

public class DemoFragmentDynamicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment_dynamic);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.main_container,
                    DemoFragmentDynamic.newInstance()
            ).commitNow();
        }
    }
}
