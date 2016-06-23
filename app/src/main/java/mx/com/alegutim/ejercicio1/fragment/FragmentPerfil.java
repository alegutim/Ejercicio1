package mx.com.alegutim.ejercicio1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mx.com.alegutim.ejercicio1.R;

/**
 * Created by Administrator on 16/06/2016.
 */
public class FragmentPerfil extends Fragment {
    private final String url_1 = "http://www.sipsych.org/files/5113/8287/6561/Male_User.png";
    private final String url_2 = "http://itsae.edu.ec/img/foto_defecto/femaleicon.png";
    ImageView imgPerfil;
    public static FragmentPerfil newInstance(String name , String ultConexion)
    {
        FragmentPerfil f = new FragmentPerfil();
        Bundle b = new Bundle();
        b.putString("key_user", name);
        b.putString("key_ult_conexion", ultConexion);
        f.setArguments(b);
        return f;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);
        imgPerfil = (ImageView)view.findViewById(R.id.imgPerfil);
        TextView textUsuario  = (TextView) view.findViewById(R.id.textUsuario);
        TextView textUltConexion  = (TextView) view.findViewById(R.id.textUltConexion);
        String user;
        String ult_conexion;
        Bundle bundle = getArguments();
        if ( bundle!=null) {
            user = bundle.getString("key_user");
            ult_conexion = bundle.getString("key_ult_conexion");
        }else{
            user = "XML inflate";
            ult_conexion = "XML inflate";
        }
        textUsuario.setText(user);
        textUltConexion.setText(ult_conexion);
        //imgPerfil.setImageResource( buscaInicial(user)?R.drawable.ic_action_spellcheck:R.drawable.ic_maps_local_parking);
        Picasso.with(getActivity()).load(buscaInicial(user)?url_1:url_2).into(imgPerfil);
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
