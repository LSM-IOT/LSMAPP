package cn.huntercat.lsmapp.demo.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // ArrayAdapter
        String[] starArray = {"水星", "金星", "地球", "火星", "木星", "土星"};
//        ArrayAdapter<String> starAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, starArray);
        ArrayAdapter<String> starAdapter = new ArrayAdapter<>(this,
                R.layout.item_select,
                starArray);
        Spinner spDropdown = findViewById(R.id.sp_dropdown);
        spDropdown.setPrompt("请选择行星");
        spDropdown.setAdapter(starAdapter);
        spDropdown.setSelection(0);
        spDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DemoWidgetActivity.this, "您选择的是" + starArray[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 未选择时的处理方法
            }
        });
        // SimpleAdapter
        List<Map<String, Object>> list = new ArrayList<>();
        int[] iconArray = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        for (int i = 0; i < iconArray.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("icon", iconArray[i]);
            item.put("name", starArray[i]);
            list.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.item_simple, new String[]{"icon", "name"}, new int[]{R.id.iv_icon, R.id.tv_name});
        simpleAdapter.setDropDownViewResource(R.layout.item_simple);
        Spinner spDialog = findViewById(R.id.sp_dialog);
        spDialog.setPrompt("请选择行星");
        spDialog.setAdapter(simpleAdapter);
        spDialog.setSelection(0);
        spDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DemoWidgetActivity.this, "您选择的是" + starArray[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // BaseAdapter

    }
}
