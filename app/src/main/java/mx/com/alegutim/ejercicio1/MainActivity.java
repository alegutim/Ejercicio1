package mx.com.alegutim.ejercicio1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.com.alegutim.ejercicio1.model.Usuario;
import mx.com.alegutim.ejercicio1.service.ServiceTimer;
import mx.com.alegutim.ejercicio1.sql.UsuarioDataSource;
import mx.com.alegutim.ejercicio1.util.SharedPreferenceUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText vUser;
    private EditText vPass;
    private UsuarioDataSource usuarioDataSource;
    private Usuario modelUsuario;
    CheckBox chkRememberMe;
    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarioDataSource = new UsuarioDataSource(getApplicationContext());
        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        int id_usuario = sharedPreferenceUtil.getLoggedUsuario();
        if (id_usuario!=-1){
            modelUsuario = usuarioDataSource.getUsuario(id_usuario);
            trueAcces();
        }
        vUser = (EditText)findViewById(R.id.activity_relative_user);
        vPass = (EditText)findViewById(R.id.activity_relative_pass);
        findViewById(R.id.activity_relative_btnlogin).setOnClickListener(this);
        findViewById(R.id.activity_relative_btnregister).setOnClickListener(this);
        chkRememberMe = (CheckBox) findViewById(R.id.chkRememberMe);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_relative_btnlogin:
                processLogin();
                break;
            case R.id.activity_relative_btnregister:
                launchRegister();
                break;
        }
    }

    private void launchRegister() {
        startActivity(new Intent(getApplicationContext(),ActivityRegistro.class));
    }

    private void processLogin() {
        final String user = vUser.getText().toString();
        final String pass = vPass.getText().toString();
        modelUsuario = usuarioDataSource.getUsuario(user,pass);
        if (modelUsuario!=null){
            if (chkRememberMe.isChecked()){
                sharedPreferenceUtil.saveLoggedUsuario(modelUsuario.id);
            }
            trueAcces();
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_log, Toast.LENGTH_SHORT).show();
        }
    }

    private void trueAcces()
    {
        Intent intent =new  Intent(getApplicationContext(),FragmentActivity.class);
        intent.putExtra("key_user",modelUsuario.usuario);
        intent.putExtra("key_ult_conexion",modelUsuario.ultConexion);
        modelUsuario.ultConexion=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ").format(new Date());
        usuarioDataSource.saveUtlConexion(modelUsuario);
        startActivity(intent);
        finish();
    }
}
