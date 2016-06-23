package mx.com.alegutim.ejercicio1.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import mx.com.alegutim.ejercicio1.DetalleActivity;
import mx.com.alegutim.ejercicio1.Ejercicio1;
import mx.com.alegutim.ejercicio1.FragmentActivity;
import mx.com.alegutim.ejercicio1.R;
import mx.com.alegutim.ejercicio1.adapters.AdapterItemList;
import mx.com.alegutim.ejercicio1.model.Elemento;
import mx.com.alegutim.ejercicio1.sql.ItemDataSource;

/**
 * Created by Administrator on 16/06/2016.
 */
public class FragmentLista extends Fragment implements View.OnClickListener {
    private ListView listView;
    private List<Elemento> array = new ArrayList<>()    ;
    private int counter;
    private EditText mItemText;
    private Boolean IsTrue = true;
    private ItemDataSource itemDataSource;
    private TextView textUsuario;
    Resources res = getResources();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDataSource = new ItemDataSource(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista,container,false);
        listView = (ListView) view.findViewById(R.id.ltsView);
        mItemText = (EditText) view.findViewById(R.id.mItemText);
        textUsuario  = (TextView) view.findViewById(R.id.textUsuario);
        view.findViewById(R.id.btnAddItem).setOnClickListener(this);
        List<Elemento> modelItemList = itemDataSource.getAllItems(Ejercicio1.USUARIO);
        listView.setAdapter(new AdapterItemList(getActivity(), modelItemList));
        if (modelItemList.size()>0) {
            IsTrue = !(modelItemList.size() % 2 == 0);
            counter = modelItemList.size();

            // muestra actividad
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AdapterItemList adapter = (AdapterItemList) parent.getAdapter();
                    Elemento modelItem = adapter.getItem(position);
                    trueAcces(modelItem.description, modelItem.item, modelItem.imagen_id);
                }
            });

            // Borrar elemento
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    AdapterItemList adapter = (AdapterItemList) parent.getAdapter();
                    final Elemento modelItem = adapter.getItem(position);

                    new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.delete_title)
                            .setMessage(String.format( res.getString(R.string.delete_inner_text) ,  modelItem.item))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    itemDataSource.deleteItem(modelItem);
                                    listView.setAdapter(new AdapterItemList(getActivity(),itemDataSource.getAllItems(Ejercicio1.USUARIO)));
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setCancelable(false).create().show();
                    return true;
                }
            });


        }
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAddItem:
                String itemData = mItemText.getText().toString() ;
                if (!TextUtils.isEmpty(itemData)){
                    Elemento item = new Elemento();
                    item.item= itemData;
                    item.description = res.getString(R.string.text_descripcion) + counter;
                    item.imagen_id = IsTrue ? R.drawable.ic_action_spellcheck:R.drawable.ic_maps_local_parking;
                    item.usuario=Ejercicio1.USUARIO;
                    Log.d(Ejercicio1.TAG,Ejercicio1.USUARIO);
                    itemDataSource.saveItem(item);
                    listView.setAdapter(new AdapterItemList(getActivity(),itemDataSource.getAllItems(Ejercicio1.USUARIO)));
                    IsTrue=!IsTrue;
                    counter++;
                }

                break;
        }
    }

    private void trueAcces(String titulo, String Detalle , int Imagen)
    {
        Intent intent =new  Intent(getActivity(),DetalleActivity.class);
        intent.putExtra("key_titulo",Detalle);
        intent.putExtra("key_detalle",titulo);
        intent.putExtra("key_imagen",Imagen);
        startActivity(intent);
    }

}
