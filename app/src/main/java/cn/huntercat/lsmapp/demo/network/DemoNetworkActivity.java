package cn.huntercat.lsmapp.demo.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.huntercat.lsmapp.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DemoNetworkActivity extends AppCompatActivity {
    private String url = "http://okhttp3.huntercat.cn/index.html";
    private final String TAG = DemoNetworkActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_network);
        TextView tvDemoOkHttpContent = findViewById(R.id.tv_demo_network_okhttp_content);
        ImageView ivImage = findViewById(R.id.iv_image);
        Button btnDemoNetworkOkHttpGet = findViewById(R.id.btn_demo_network_okhttp_get);
        btnDemoNetworkOkHttpGet.setOnClickListener(v -> {
            OkHttpClient client = new OkHttpClient(); // 创建客户端对象
            Request request = new Request.Builder() // 创建请求对象
//                    .header("Accept-Language", "zh-CN")
                    .url(url)
                    .build();
            Call call = client.newCall(request); // 根据请求构建调用对象
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    InputStream inputStream = response.body().byteStream();
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    String mediaType = response.body().contentType().toString();
                    long length = response.body().contentLength();
                    String desc = String.format("文件类型为%s,文件大小为%d", mediaType, length);
//                    runOnUiThread(() -> {
//                        // 操作主线程内容
//                        tvDemoOkHttpContent.setText("下载网络图片返回：" + desc);
//                        ivImage.setImageBitmap(bitmap);
//                    });
                    String privatePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
                    String fileName = privatePath + "/" + url.substring(url.lastIndexOf("/") + 1);
                    File newFile = new File(fileName);
                    FileOutputStream fileOutputStream = new FileOutputStream(newFile);

                    byte[] buf = new byte[2048];
                    int len = 0;
                    long sum = 0;
                    while ((len = inputStream.read(buf)) != -1) {
                        fileOutputStream.write(buf, 0, len);
                        sum += len;
                    }
                    fileOutputStream.flush();
                    Log.i(TAG, "写入成功");
                }
            });
        });

    }
}
