package cn.huntercat.lsmapp.demo.andserver;

import android.content.Context;
import android.os.Environment;

import com.yanzhenjie.andserver.annotation.Config;
import com.yanzhenjie.andserver.framework.config.Multipart;
import com.yanzhenjie.andserver.framework.config.WebConfig;
import com.yanzhenjie.andserver.framework.website.StorageWebsite;
import com.yanzhenjie.andserver.framework.website.Website;

import java.io.File;

@Config
public class DemoAndServerWebConfig implements WebConfig {
    @Override
    public void onConfig(Context context, Delegate delegate) {

//        File file = new File(Environment.getExternalStorageDirectory(), "www");
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString());
        String websiteDirectory = file.getAbsolutePath();

        Website wesite = new StorageWebsite(websiteDirectory);
        delegate.addWebsite(wesite);
//        delegate.addWebsite(new AssetsWebsite(context, "/web"));

        delegate.setMultipart(Multipart.newBuilder()
//            .allFileMaxSize(1024 * 1024 * 20) // 20M
//            .fileMaxSize(1024 * 1024 * 5) // 5M
//            .maxInMemorySize(1024 * 10) // 1024 * 10 bytes
                .uploadTempDir(new File(context.getCacheDir(), "_server_upload_cache_")).build());
    }
}
