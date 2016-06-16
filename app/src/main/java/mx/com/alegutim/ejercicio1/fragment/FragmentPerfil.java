package mx.com.alegutim.ejercicio1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mx.com.alegutim.ejercicio1.R;

/**
 * Created by Administrator on 16/06/2016.
 */
public class FragmentPerfil extends Fragment {
    ImageView imgPerfil;
    public static FragmentPerfil newInstance(String name)
    {
        FragmentPerfil f = new FragmentPerfil();
        Bundle b = new Bundle();
        b.putString("key_user", name);
        f.setArguments(b);
        return f;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);
        imgPerfil = (ImageView)view.findViewById(R.id.imgPerfil);
        TextView textUsuario  = (TextView) view.findViewById(R.id.textUsuario);
        String user;
        Bundle bundle = getArguments();
        if ( bundle!=null) {
            user = bundle.getString("key_user");
        }else{
            user = "XML inflate";
        }
        textUsuario.setText(user);
        return view;
    }


}
