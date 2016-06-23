package mx.com.alegutim.ejercicio1.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import mx.com.alegutim.ejercicio1.util.SharedPreferenceUtil;

/**
 * Created by Administrator on 23/06/2016.
 */
public class ServiceTimer extends Service {
    public static final String ACTION_SEND_TIMER="mx.com.alegutim.ejercicio1.SEND_TIMER";
    SharedPreferenceUtil sharedPreferenceUtil;

    int counter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            counter++;
            handler.postDelayed(runnable,1000);
            Intent i = new Intent((ACTION_SEND_TIMER));
            i.putExtra("timer",counter);
            sendBroadcast(i);
            //Log.d("EJERCICIO1","contador " + counter);
        }
    } ;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        int timer_session = sharedPreferenceUtil.getTimeSession();
        if (timer_session!=-1){
            counter=timer_session;
        }
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
