package cn.huntercat.lsmapp.demo.file;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import cn.huntercat.lsmapp.R;

public class DemoFileActivity extends AppCompatActivity {

    private final String TAG = DemoFileActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_file);
        Context context = this.getApplicationContext();
        String cacheDir = context.getCacheDir().getPath();
        String filesDir = context.getFilesDir().getPath();
        Log.i(TAG, "cacheDir:" + cacheDir);
        Log.i(TAG, "filesDir" + filesDir);
        // 获得系统根目录的路径。
        Log.i(TAG, "Environment.getRootDirectory:" + Environment.getRootDirectory());
        // 获得系统数据目录的路径。
        Log.i(TAG, "Environment.getDataDirectory:" + Environment.getDataDirectory());
        // 获得下载缓存目录的路径。
        Log.i(TAG, "Environment.getDownloadCacheDirectory:" + Environment.getDownloadCacheDirectory());
        // 获得SD卡的路径。
        Log.i(TAG, "Environment.getExternalStorageDirectory:" + Environment.getExternalStorageDirectory());
        // 获得SD卡的状态。
        Log.i(TAG, "Environment.getExternalStorageState:" + Environment.getExternalStorageState());
//        Log.i(TAG, "Environment.getRootDirectory:" + Environment.getStorageState());
        Log.i(TAG, "Environment.下载目录:" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        Log.i(TAG, "Environment.相机目录:" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
        Log.i(TAG, "Environment.文档目录:" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));

        String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        int PERMISSION_CODE = 123;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(DemoFileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DemoFileActivity.this, PERMISSIONS, PERMISSION_CODE);
            }
        }


        File myFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/www");
        // 创建文件夹
        if (!myFile.isDirectory()) {
            myFile.mkdir();
        }

        // 创建文件
        File tempFile = new File(myFile.getAbsolutePath() + "/index.html");
        if (tempFile.exists()) {
            tempFile.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            String content = "" +
                    "<html><head><meta charset=\"utf-8\"></head><body>" +
                    "<h1>这是我的安卓服务器缓存的网页</h1>" +
                    "<video src=\"video.mp4\" controls></video>" +
                    "<body></html>";
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
