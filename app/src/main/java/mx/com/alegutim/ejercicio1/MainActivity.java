package mx.com.alegutim.ejercicio1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText vUser;
    private EditText vPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vUser = (EditText)findViewById(R.id.activity_relative_user);
        vPass = (EditText)findViewById(R.id.activity_relative_pass);
        findViewById(R.id.activity_relative_btnlogin).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_relative_btnlogin:
                processLogin();
                break;
        }
    }

    private void processLogin() {
        final String user = vUser.getText().toString();
        final String pass = vPass.getText().toString();
        if (user.equals("alegutim") && pass.equals("1234a")) {
            trueAcces();
        } else if (user.equals("zusuario") && pass.equals("1234a")) {
            trueAcces();
        } else {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    private void trueAcces()
    {
        Intent intent =new  Intent(getApplicationContext(),FragmentActivity.class);
        intent.putExtra("key_user",vUser.getText().toString());
        startActivity(intent);
    }
}
