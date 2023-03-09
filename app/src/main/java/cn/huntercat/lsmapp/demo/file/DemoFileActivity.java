package cn.huntercat.lsmapp.demo.file;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

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
                Log.i(TAG, "写入成功");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void test() {
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
            String content = "" + "<html><head><meta charset=\"utf-8\"></head><body>" + "<h1>这是我的安卓服务器缓存的网页</h1>" + "<video src=\"video.mp4\" controls></video>" + "<body></html>";
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
