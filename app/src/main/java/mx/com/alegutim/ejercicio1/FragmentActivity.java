package mx.com.alegutim.ejercicio1;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mx.com.alegutim.ejercicio1.fragment.FragmentLista;
import mx.com.alegutim.ejercicio1.fragment.FragmentPerfil;
import mx.com.alegutim.ejercicio1.util.SharedPreferenceUtil;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    String usuario;
    String ultConexion;
    SharedPreferenceUtil sharedPreferenceUtil;
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
}
