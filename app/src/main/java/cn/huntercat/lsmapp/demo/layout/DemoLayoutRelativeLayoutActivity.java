package cn.huntercat.lsmapp.demo.layout;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.huntercat.lsmapp.R;

public class DemoLayoutRelativeLayoutActivity extends AppCompatActivity {

    private static final String TAG = DemoLayoutRelativeLayoutActivity.class.getSimpleName();

    private TextView tvHeadAdlrl, tvContentAdlrl;

    private String strHeadAdlrl = "相对布局 RelativeLayout";
    private String strContentAdlrl = "相对布局是通过指定当前控件与兄弟控件或者是父控件之间的相对位置，从而达到控制控件位置的目的。\n" +
            "相对布局属性：方向位置，上方、下方、左方、右方。\n" +
            "相对布局属性：方向对齐，上方、下方、左方、右方。\n" +
            "相对布局属性：基准线对齐，android:layout_alignBaseline。\n" +
            "相对布局属性：父控件边缘对齐，android:layout_alignParentLeft。\n" +
            "相对布局属性：父控件中央对齐，android:layout_centerInParent。\n" +
            "相对布局新属性：头部/尾部对齐。";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_layout_relativelayout);

        tvHeadAdlrl = findViewById(R.id.tv_header_adlrl);
        tvContentAdlrl = findViewById(R.id.tv_content_adlrl);

        tvHeadAdlrl.setText(strHeadAdlrl);
        tvContentAdlrl.setText(strContentAdlrl);


    }
}
