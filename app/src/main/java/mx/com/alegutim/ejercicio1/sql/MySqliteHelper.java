package mx.com.alegutim.ejercicio1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 22/06/2016.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME  = "ejercicio1_msqlite";
    private final static int DATABASE_VERSION=1;
    public static final String TABLE_NAME_USUARIOS = "tabla_usuarios";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ITEM_USUARIO= "usuario";
    public static final String COLUMN_ITEM_PASSWORD= "password";
    public static final String COLUMN_ITEM_ULTCON= "utl_conexion";
    private static final String CREATE_TABLE_USUARIOS = "create table " + TABLE_NAME_USUARIOS
            + " ( " + COLUMN_ID + " integer primary key autoincrement ," +
            COLUMN_ITEM_USUARIO + " text not null , "+
            COLUMN_ITEM_PASSWORD + " text not null , "+
            COLUMN_ITEM_ULTCON + " text not null )";

    public static final String TABLE_NAME_ITEM = "tabla_item";
    public static final String COLUMN_ID_ITEM = BaseColumns._ID;
    public static final String COLUMN_ITEM_NAME= "name";
    public static final String COLUMN_ITEM_DESC= "description";
    public static final String COLUMN_ITEM_RESOURCE= "resource_id";
    public static final String COLUMN_ITEM_USUARIO_ITEM= "usuario";

    private static final String CREATE_TABLE_ITEMS = "create table " + TABLE_NAME_ITEM
            + " ( " + COLUMN_ID_ITEM + " integer primary key autoincrement ," +
            COLUMN_ITEM_NAME + " text not null , "+
            COLUMN_ITEM_DESC + " text not null , "+
            COLUMN_ITEM_RESOURCE + " integer not null, "+
            COLUMN_ITEM_USUARIO_ITEM + " text not null )";

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIOS);
        db.execSQL(CREATE_TABLE_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
