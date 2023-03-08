package cn.huntercat.lsmapp.demo.widget;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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


        CheckBox cbDemoWidget1 = findViewById(R.id.cb_demo_widget_1);
        cbDemoWidget1.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(DemoWidgetActivity.this,
                        String.format("您%s了这个CheckBox", isChecked ? "勾选" : "取消勾选"),
                        Toast.LENGTH_SHORT).show());


        TextView rgSex = findViewById(R.id.tv_sex);
        RadioGroup radioGroup = findViewById(R.id.rb_sex);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_male) {
                    rgSex.setText("男");
                } else if (checkedId == R.id.rb_female) {
                    rgSex.setText("女");
                }
            }
        });

    }
}
