package mx.com.alegutim.ejercicio1.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.com.alegutim.ejercicio1.Ejercicio1;
import mx.com.alegutim.ejercicio1.model.Elemento;

/**
 * Created by Administrator on 23/06/2016.
 */
public class ItemDataSource {
    private final SQLiteDatabase db;

    public ItemDataSource(Context context){
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void  saveItem(Elemento modelItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_ITEM_NAME,modelItem.item);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_DESC,modelItem.description);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_RESOURCE,modelItem.imagen_id);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_USUARIO_ITEM,modelItem.usuario);
        db.insert(MySqliteHelper.TABLE_NAME_ITEM,null,contentValues);
        Log.d(Ejercicio1.TAG,"Insert OK");
    }

    public  void deleteItem (Elemento modelItem){
        db.delete(MySqliteHelper.TABLE_NAME_ITEM,MySqliteHelper.COLUMN_ID + " =? ", new String[]{String.valueOf(modelItem.id)});
    }

    public List<Elemento> getAllItems(String user){
        List<Elemento> modelItemList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME_ITEM,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_NAME));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_DESC));
            int resource_id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_RESOURCE));
            String usuario = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_USUARIO_ITEM));
            Elemento modelItem = new Elemento();
            modelItem.id = id;
            modelItem.item = itemName;
            modelItem.description = descripcion;
            modelItem.imagen_id = resource_id;
            modelItem.usuario = usuario;
            if (user.equals(usuario)) {
                modelItemList.add(modelItem);
            }
        }
        return modelItemList;
    }


}
