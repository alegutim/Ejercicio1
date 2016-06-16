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
        imgPerfil.setImageResource( buscaInicial(user)?R.drawable.ic_action_spellcheck:R.drawable.ic_maps_local_parking);
        return view;
    }

    public Boolean buscaInicial(String usuario){
        Boolean result = false ;

        String[] primerparte = new String[12];
        primerparte[0]="a";
        primerparte[1]="b";
        primerparte[2]="c";
        primerparte[3]="d";
        primerparte[4]="e";
        primerparte[5]="f";
        primerparte[6]="g";
        primerparte[7]="h";
        primerparte[8]="i";
        primerparte[9]="j";
        primerparte[10]="k";
        primerparte[11]="m";

        for (int x=0; x<primerparte.length-1; x++){
            if(primerparte[x].toString().equals(usuario.substring(0,1))) {
            result = true;
            }
        }
        return  result;
    }


}
