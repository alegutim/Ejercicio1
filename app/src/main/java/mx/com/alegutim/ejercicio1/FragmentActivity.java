package mx.com.alegutim.ejercicio1;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import mx.com.alegutim.ejercicio1.fragment.FragmentLista;
import mx.com.alegutim.ejercicio1.fragment.FragmentPerfil;
import mx.com.alegutim.ejercicio1.service.ServiceTimer;
import mx.com.alegutim.ejercicio1.util.SharedPreferenceUtil;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    String usuario;
    String ultConexion;
    TextView txvSesion;
    SharedPreferenceUtil sharedPreferenceUtil;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getExtras().getInt("timer");
            txvSesion.setText(String.valueOf(count));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
         usuario = getIntent().getExtras().getString("key_user");
        ultConexion = getIntent().getExtras().getString("key_ult_conexion");
        findViewById(R.id.btnperfil).setOnClickListener(this);
        findViewById(R.id.btnlista).setOnClickListener(this);
        findViewById(R.id.btncSesion).setOnClickListener(this);
        txvSesion = (TextView) findViewById(R.id.txvSesion);
        startService(new Intent(getApplicationContext(), ServiceTimer.class));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnperfil:
                showPerfil();
                break;
            case R.id.btnlista:
                showList();
                break;
            case R.id.btncSesion:
                eliminaUsuarioLogueado();
                break;

        }
    }

    private void eliminaUsuarioLogueado() {
        sharedPreferenceUtil.saveLoggedUsuario(-1);
        Intent intent =new  Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void showPerfil() {
        FragmentPerfil f = FragmentPerfil.newInstance(usuario,ultConexion);
        getFragmentManager().beginTransaction().replace(R.id.fragment_perfil,f).commit();


    }

    private void showList() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentLista()).commit();

    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServiceTimer.ACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver,intentFilter);
        }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferenceUtil.saveTimeSession(Integer.parseInt(txvSesion.getText().toString()));
        stopService(new Intent(getApplicationContext(),ServiceTimer.class));
    }
}
