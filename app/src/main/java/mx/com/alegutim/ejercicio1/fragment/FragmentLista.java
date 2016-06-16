package mx.com.alegutim.ejercicio1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.com.alegutim.ejercicio1.R;
import mx.com.alegutim.ejercicio1.adapters.AdapterItemList;
import mx.com.alegutim.ejercicio1.model.Elemento;

/**
 * Created by Administrator on 16/06/2016.
 */
public class FragmentLista extends Fragment implements View.OnClickListener {
    private ListView listView;
    private List<Elemento> array = new ArrayList<>()    ;
    private int counter;
    private EditText mItemText;
    private Boolean IsTrue = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista,container,false);
        listView = (ListView) view.findViewById(R.id.ltsView);
        mItemText = (EditText) view.findViewById(R.id.mItemText);
        view.findViewById(R.id.btnAddItem).setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter = (AdapterItemList) parent.getAdapter();
                Elemento modelItem = adapter.getItem(position);
                Elemento modelItem2 = array.get(position);
                Toast.makeText(getActivity(),modelItem2.item,Toast.LENGTH_SHORT).show();
            }
        });

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

                    item.id = "Description: " + counter;
                    item.imagen_id = IsTrue ? R.drawable.ic_action_spellcheck:R.drawable.ic_maps_local_parking;
                    array.add(item);
                    listView.setAdapter(new AdapterItemList(getActivity(),array));
                    IsTrue=!IsTrue;
                    counter++;
                }

                break;
        }
    }

}
