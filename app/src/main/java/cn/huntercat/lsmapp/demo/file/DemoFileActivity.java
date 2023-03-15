package cn.huntercat.lsmapp.demo.file;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import cn.huntercat.lsmapp.R;

public class DemoFileActivity extends AppCompatActivity {

    private final String TAG = DemoFileActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_file);
        Context context = this.getApplicationContext();
        Button btnDemoFileFind = findViewById(R.id.btn_demo_file_find);
        btnDemoFileFind.setOnClickListener(v -> {
            String cacheDir = context.getCacheDir().getPath();
            String filesDir = context.getFilesDir().getPath();
            String publicPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            String privatePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
            Log.i(TAG, "publicPath:" + publicPath);
            Log.i(TAG, "privatePath:" + privatePath);
            Log.i(TAG, "cacheDir:" + cacheDir);
            Log.i(TAG, "filesDir" + filesDir);
            Log.i(TAG, "Environment.获得系统根目录的路径:" + Environment.getRootDirectory());
            Log.i(TAG, "Environment.获得系统数据目录的路径:" + Environment.getDataDirectory());
            Log.i(TAG, "Environment.获得下载缓存目录的路径:" + Environment.getDownloadCacheDirectory());
            Log.i(TAG, "Environment.获得SD卡的路径:" + Environment.getExternalStorageDirectory());
            Log.i(TAG, "Environment.获得SD卡的状态:" + Environment.getExternalStorageState());
            Log.i(TAG, "Environment.下载目录:" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            Log.i(TAG, "Environment.相机目录:" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
            Log.i(TAG, "Environment.文档目录:" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
        });
        //test();
        Button btnDemoFileWritePrivate = findViewById(R.id.btn_demo_file_write_private);
        btnDemoFileWritePrivate.setOnClickListener(v -> {
            String privatePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
            File newFile = new File(privatePath + "/test.txt");
            String text = "测试内容";
            try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
                fileOutputStream.write(text.getBytes());
                Log.i(TAG, "写入地址：" + newFile.getAbsolutePath());
                Log.i(TAG, "写入成功");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void updateApk(View view) {
        try {
            Process process = Runtime.getRuntime().exec("su");
            PrintWriter printWriter = new PrintWriter(process.getOutputStream());
            printWriter.println("pm install -r /storage/emulated/0/Android/data/app-debug.apk");
            printWriter.flush();
            printWriter.close();
            Process process2 = Runtime.getRuntime().exec("su");
            DataOutputStream dataOutputStream = new DataOutputStream(process2.getOutputStream());
            dataOutputStream.writeBytes("sleep 1; am start cn.huntercat.lsmapp/.MainActivity");
            dataOutputStream.flush();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
