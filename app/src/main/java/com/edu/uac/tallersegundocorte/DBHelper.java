package com.edu.uac.tallersegundocorte;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    String bd = "CREATE TABLE platos (codigo INTEGER PRIMARY KEY, "
            +"nombre TEXT, "
            +"descripcion TEXT, "
            +"tipoplato TEXT,"
            +"tipocomida TEXT, "
            +"precio INTEGER);";


    public DBHelper(Context context) {
        super(context, "platos", null, 1);
    }


    public void agregarPlato(int codigo, String nombre, String descripcion, String tipoplato, String tipocomida, int precio)
    {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String insert = "INSERT INTO platos (codigo, nombre, descripcion, tipoplato, tipocomida, precio) "
                +"VALUES ("
                +codigo
                +", '"
                +nombre
                +"', '"
                +descripcion
                +"', '"
                +tipoplato
                +"', '"
                +tipocomida
                +"', '"
                +precio
                +"')";

        basedatos.execSQL(insert);
    }

    public void eliminarPlato(int codigo)
    {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String delete = "DELETE FROM platos "
                +"WHERE codigo = "
                +codigo
                +"";

        basedatos.execSQL(delete);
    }

    public void modificarPlato(int codigo, String nombre, String descripcion, String tipoplato, String tipocomida, int precio)
    {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String update = "UPDATE platos "
                +"SET nombre = '"
                +nombre
                +"', descripcion = '"
                +descripcion
                +"', tipoplato = '"
                +tipoplato
                +"', tipocomida = '"
                +tipocomida
                +"' , precio = "
                +precio
                +" "
                +"WHERE codigo = "
                +codigo
                +"";

        basedatos.execSQL(update);
    }

    public Cursor buscarPlato_pornombre(String nombre)
    {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String search = "SELECT codigo, nombre, descripcion, tipoplato, tipocomida, precio "
                +"FROM platos "
                +"WHERE nombre = '"
                +nombre
                +"'";

        Cursor c = basedatos.rawQuery(search, null);
        c.moveToFirst();
        return c;
    }

    public Cursor buscarPlato_porcodigo(int codigo)
    {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String search = "SELECT codigo, nombre, descripcion, tipoplato, tipocomida, precio "
                +"FROM platos "
                +"WHERE codigo = "
                +codigo
                +"";

        Cursor c = basedatos.rawQuery(search, null);
        c.moveToFirst();
        return c;
    }

    public Cursor buscarPlato_portipodecomida(String tc)
    {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String search = "SELECT codigo as _id, nombre, descripcion, tipoplato, tipocomida, precio "
                +"FROM platos "
                +"WHERE tipocomida = '"
                +tc
                +"'";

        Cursor c = basedatos.rawQuery(search, null);
        c.moveToFirst();
        return c;
    }


    public Cursor listarplatos()
    {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String show = "SELECT codigo as _id, nombre, descripcion, tipoplato, tipocomida, precio "
                +"FROM platos";

        Cursor c = basedatos.rawQuery(show, null);
        c.moveToFirst();
        return c;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(bd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS platos");
        db.execSQL(bd);
    }

}
