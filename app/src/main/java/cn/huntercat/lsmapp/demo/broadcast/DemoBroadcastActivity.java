package cn.huntercat.lsmapp.demo.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import cn.huntercat.lsmapp.R;

public class DemoBroadcastActivity extends AppCompatActivity {

    private static final String STANDARD_ACTION = "cn.huntercat.lsmapp.demo.broadcast.DemoBroadcastActivity";

    private TextView tvDemoBroadcastContent;

    private MyReceiver myReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(STANDARD_ACTION);
        registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_broadcast);
        tvDemoBroadcastContent = findViewById(R.id.tv_demo_broadcast_content);
        findViewById(R.id.btn_demo_broadcast_send).setOnClickListener(v -> {
//            Intent intent = new Intent(STANDARD_ACTION); // 创建指定动作的意图
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    //闹钟的小时
                    .putExtra(AlarmClock.EXTRA_HOUR, 16)
                    //闹钟的分钟
                    .putExtra(AlarmClock.EXTRA_MINUTES, 30)
                    //响铃时提示的信息
                    .putExtra(AlarmClock.EXTRA_MESSAGE, "秘书宝设置的闹钟")
                    //用于指定该闹铃触发时是否振动
                    .putExtra(AlarmClock.EXTRA_VIBRATE, true)
                    //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
                    //如需使用默认铃声，则无需指定此 extra。
//                    .putExtra(AlarmClock.EXTRA_RINGTONE, ringtoneUri)
                    //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
                    // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
                    //对于一次性闹铃，无需指定此 extra
//                    .putExtra(AlarmClock.EXTRA_DAYS, testDays)
                    //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
                    .putExtra(AlarmClock.EXTRA_SKIP_UI, true); // 发送系统闹钟设置广播
//
//            Intent alarms = new Intent(AlarmClock.ACTION_SET_ALARM);

            startActivity(intent);
            Toast.makeText(this, "秘书宝提示：闹钟设置完成！", Toast.LENGTH_SHORT).show();
//            sendBroadcast(intent); // 发送标准广播
//            DemoBroadcastActivity.setAlarm(this, 1, 1, 1, 0, 0, "闹钟响了", 0);
        });
    }

    public static final String ALARM_ACTION = "com.loonggg.alarm.clock";

    public static void setAlarm(Context context, int flag, int hour, int minute, int id, int
            week, String tips, int soundOrVibrator) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        long intervalMillis = 0;
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                (Calendar.DAY_OF_MONTH), hour, minute, 10);
        if (flag == 0) {
            intervalMillis = 0;
        } else if (flag == 1) {
            intervalMillis = 24 * 3600 * 1000;
        } else if (flag == 2) {
            intervalMillis = 24 * 3600 * 1000 * 7;
        }
        Intent intent = new Intent(ALARM_ACTION);
        intent.putExtra("intervalMillis", intervalMillis);
        intent.putExtra("msg", tips);
        intent.putExtra("id", id);
        intent.putExtra("soundOrVibrator", soundOrVibrator);
        PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, PendingIntent
                .FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setWindow(AlarmManager.RTC_WAKEUP, calMethod(week, calendar.getTimeInMillis()),
                    intervalMillis, sender);
        } else {
            if (flag == 0) {
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
            } else {
                am.setRepeating(AlarmManager.RTC_WAKEUP, calMethod(week, calendar.getTimeInMillis
                        ()), intervalMillis, sender);
            }
        }
    }

    /**
     * @param weekflag 传入的是周几
     * @param dateTime 传入的是时间戳（设置当天的年月日+从选择框拿来的时分秒）
     * @return 返回起始闹钟时间的时间戳
     */
    private static long calMethod(int weekflag, long dateTime) {
        long time = 0;
        //weekflag == 0表示是按天为周期性的时间间隔或者是一次行的，weekfalg非0时表示每周几的闹钟并以周为时间间隔
        if (weekflag != 0) {
            Calendar c = Calendar.getInstance();
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (1 == week) {
                week = 7;
            } else if (2 == week) {
                week = 1;
            } else if (3 == week) {
                week = 2;
            } else if (4 == week) {
                week = 3;
            } else if (5 == week) {
                week = 4;
            } else if (6 == week) {
                week = 5;
            } else if (7 == week) {
                week = 6;
            }

            if (weekflag == week) {
                if (dateTime > System.currentTimeMillis()) {
                    time = dateTime;
                } else {
                    time = dateTime + 7 * 24 * 3600 * 1000;
                }
            } else if (weekflag > week) {
                time = dateTime + (weekflag - week) * 24 * 3600 * 1000;
            } else if (weekflag < week) {
                time = dateTime + (weekflag - week + 7) * 24 * 3600 * 1000;
            }
        } else {
            if (dateTime > System.currentTimeMillis()) {
                time = dateTime;
            } else {
                time = dateTime + 24 * 3600 * 1000;
            }
        }
        return time;
    }


    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(STANDARD_ACTION)) {
                tvDemoBroadcastContent.setText("这里查看广播信息");
            }
        }
    }
}
