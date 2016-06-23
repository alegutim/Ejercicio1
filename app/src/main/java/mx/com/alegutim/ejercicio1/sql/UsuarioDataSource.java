package mx.com.alegutim.ejercicio1.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.com.alegutim.ejercicio1.model.Usuario;

/**
 * Created by Administrator on 22/06/2016.
 */
public class UsuarioDataSource {
    private final SQLiteDatabase db;

    public UsuarioDataSource(Context context){
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void  saveUsuario(Usuario usuarioItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_ITEM_USUARIO,usuarioItem.usuario);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_PASSWORD,usuarioItem.password);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_ULTCON,usuarioItem.ultConexion);
        db.insert(MySqliteHelper.TABLE_NAME_USUARIOS,null,contentValues);
    }

    public Usuario getUsuario(String user , String pass){
        Usuario modelUsuario = null;
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME_USUARIOS,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String usuario = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_USUARIO));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_PASSWORD));
            String utl_conexion = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_ULTCON));
            if (user.equals(usuario) && pass.equals(password)){
                modelUsuario = new Usuario(id,usuario,password,utl_conexion);
            }
        }
        return modelUsuario;
    }

    public Usuario getUsuario(int id_usuario){
        Usuario modelUsuario = null;
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME_USUARIOS,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String usuario = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_USUARIO));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_PASSWORD));
            String utl_conexion = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_ULTCON));
            if (id_usuario==id){
                modelUsuario = new Usuario(id,usuario,password,utl_conexion);
            }
        }
        return modelUsuario;
    }

    public  void saveUtlConexion (Usuario modelUsuario){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_ITEM_ULTCON,modelUsuario.ultConexion);
        db.update(MySqliteHelper.TABLE_NAME_USUARIOS,contentValues,MySqliteHelper.COLUMN_ID + " =? ", new String[]{String.valueOf(modelUsuario.id)});
    }
}
