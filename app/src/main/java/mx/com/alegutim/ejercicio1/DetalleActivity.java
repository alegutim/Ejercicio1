package mx.com.alegutim.ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        String titulo = getIntent().getExtras().getString("key_titulo");
        String detalle = getIntent().getExtras().getString("key_detalle");
        int imagen_id = getIntent().getExtras().getInt("key_imagen");

        TextView det_tit = (TextView)findViewById(R.id.det_tit);
        TextView det_desc = (TextView)findViewById(R.id.det_desc);
        ImageView det_image = (ImageView)findViewById(R.id.det_image);

        det_tit.setText(titulo);
        det_desc.setText(detalle);
        det_image.setImageResource(imagen_id);
    }
}
