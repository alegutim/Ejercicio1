package mx.com.alegutim.ejercicio1;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mx.com.alegutim.ejercicio1.fragment.FragmentLista;
import mx.com.alegutim.ejercicio1.fragment.FragmentPerfil;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
         usuario = getIntent().getExtras().getString("key_user");
        findViewById(R.id.btnperfil).setOnClickListener(this);
        findViewById(R.id.btnlista).setOnClickListener(this);

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

        }
    }

    private void showPerfil() {
        FragmentPerfil f = FragmentPerfil.newInstance(usuario);
        getFragmentManager().beginTransaction().replace(R.id.fragment_perfil,f).commit();


    }

    private void showList() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentLista()).commit();

    }
}
