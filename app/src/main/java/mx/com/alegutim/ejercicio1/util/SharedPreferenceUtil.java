package mx.com.alegutim.ejercicio1.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 23/06/2016.
 */
public class SharedPreferenceUtil {

    private  final SharedPreferences sp;
    private static final String FILE_NAME_LOGGED_USER = "ejercicio1_shared_preferences";

    public SharedPreferenceUtil(Context context) {
        sp = context.getSharedPreferences(FILE_NAME_LOGGED_USER,Context.MODE_PRIVATE);
    }

    public void saveLoggedUsuario (int id){
        sp.edit().putString("id_user",String.valueOf(id)).apply();
    }

    public int getLoggedUsuario (){
        int id_user = Integer.parseInt(sp.getString("id_user","-1"));
        return id_user;
    }

    public void saveTimeSession (int id){
        sp.edit().putString("timer_session",String.valueOf(id)).apply();
    }

    public int getTimeSession (){
        int id_user = Integer.parseInt(sp.getString("timer_session","-1"));
        return id_user;
    }


}
