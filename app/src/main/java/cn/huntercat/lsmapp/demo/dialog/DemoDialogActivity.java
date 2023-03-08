package cn.huntercat.lsmapp.demo.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import cn.huntercat.lsmapp.R;

public class DemoDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_dialog);

        findViewById(R.id.btn_demo_dialog_alert).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("尊敬的用户");
            builder.setMessage("你真的要卸载我吗？");
            builder.setPositiveButton("残忍卸载", (dialog, which) -> Toast.makeText(DemoDialogActivity.this, "虽然依依不舍，但只能离开了", Toast.LENGTH_SHORT).show());
            builder.setNegativeButton("我再想想", (dialog, which) -> Toast.makeText(DemoDialogActivity.this, "让我再陪你365个日夜", Toast.LENGTH_SHORT).show());
            AlertDialog alertDialog = builder.create(); // 构建对话框对象
            alertDialog.show();// 显示对话框
        });
        findViewById(R.id.btn_demo_dialog_date).setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> Toast.makeText(DemoDialogActivity.this, String.format("您选择的日期是%d年%d月%d日", year, month + 1, dayOfMonth), Toast.LENGTH_SHORT).show(), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });
        findViewById(R.id.btn_demo_dialog_time).setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog = new TimePickerDialog(this, (TimePickerDialog.OnTimeSetListener) (view, hourOfDay, minute) -> Toast.makeText(DemoDialogActivity.this, String.format("您选择的时间是%d时%d分", hourOfDay, minute), Toast.LENGTH_SHORT).show(), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            dialog.show();
        });

    }
}
