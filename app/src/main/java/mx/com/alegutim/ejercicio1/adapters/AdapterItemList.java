package mx.com.alegutim.ejercicio1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.com.alegutim.ejercicio1.R;
import mx.com.alegutim.ejercicio1.model.Elemento;

/**
 * Created by Administrator on 16/06/2016.
 */
public class AdapterItemList extends ArrayAdapter<Elemento> {
    public AdapterItemList(Context context, List<Elemento> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_layout, parent, false);
        }
        TextView txtItemDescription = (TextView) convertView.findViewById(R.id.txtTexto);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitulo);
        ImageView img = (ImageView) convertView.findViewById(R.id.aleatory_image);
        Elemento modelitem = getItem(position);
        txtTitle.setText(modelitem.item);
        txtItemDescription.setText(modelitem.id);
        img.setImageResource(modelitem.imagen_id);
        return convertView;
    }
}