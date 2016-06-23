package mx.com.alegutim.ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.com.alegutim.ejercicio1.model.Usuario;
import mx.com.alegutim.ejercicio1.sql.UsuarioDataSource;

public class ActivityRegistro extends AppCompatActivity {
    private UsuarioDataSource usuarioDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EditText txtUserRegister = (EditText) findViewById(R.id.txtUserRegister);
        final EditText txtPassRegister = (EditText) findViewById(R.id.txtPassRegister);
        findViewById(R.id.btnRegisterUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vuser = txtUserRegister.getText().toString();
                String vpass = txtPassRegister.getText().toString();
                usuarioDataSource = new UsuarioDataSource(getApplicationContext());
                usuarioDataSource.saveUsuario(new Usuario(vuser,vpass,new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ").format(new Date())));
                finish();

            }
        });
    }
}
