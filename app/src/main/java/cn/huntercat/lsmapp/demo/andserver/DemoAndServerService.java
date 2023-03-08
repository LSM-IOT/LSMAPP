package cn.huntercat.lsmapp.demo.andserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class DemoAndServerService extends Service {

    private static final String TAG = DemoAndServerService.class.getSimpleName();

    private Server server;

    @Override
    public void onCreate() {
        server = AndServer.webServer(this)
                .port(8080)
                .timeout(10, TimeUnit.SECONDS)
                .listener(new Server.ServerListener() {
                    @Override
                    public void onStarted() {
                        InetAddress address = NetUtils.getLocalIPAddress();
                        DemoAndServerServiceManager.onServerStart(DemoAndServerService.this, address.getHostAddress());
                    }

                    @Override
                    public void onStopped() {
                        DemoAndServerServiceManager.onServerStop(DemoAndServerService.this);
                    }

                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                        DemoAndServerServiceManager.onServerError(DemoAndServerService.this, e.getMessage());
                    }
                }).build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startServer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopServer();
        super.onDestroy();
    }

    private void startServer() {
        Log.i(TAG, "startServer:START");
        server.startup();
        Log.i(TAG, "startServer:END");
    }

    private void stopServer() {
        server.shutdown();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
