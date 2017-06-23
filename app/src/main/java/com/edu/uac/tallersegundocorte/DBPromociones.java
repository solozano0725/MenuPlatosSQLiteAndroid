package com.edu.uac.tallersegundocorte;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sol Mayra on 06/05/2017.
 */

public class DBPromociones extends SQLiteOpenHelper {

    String bd = "CREATE TABLE promociones (codigo INTEGER PRIMARY KEY, "
            +"titulo TEXT,"
            +"descripcion TEXT, "
            +"valor INTEGER);";

    public DBPromociones(Context context) {
        super(context, "promociones", null, 1);
    }

    public void agregarPlato() {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String insert1 = "INSERT INTO promociones (codigo, titulo, descripcion, valor) "
                +"VALUES (1, '2X1 en Cervezas', 'En cerveza nacionales', 3000)";
        String insert2 = "INSERT INTO promociones (codigo, titulo, descripcion, valor) "
                +"VALUES (2, 'Lunes de Pastas', 'Ñoquis, pastas napolitana a mitad de precio.', 8000)";
        String insert3 = "INSERT INTO promociones (codigo, titulo, descripcion, valor) "
                +"VALUES (3, 'Miercoles de After Office', '1 litro de Cerveza Artesanal con -20%', 15000)";
        String insert4 = "INSERT INTO promociones (codigo, titulo, descripcion, valor) "
                +"VALUES (4, 'Sabado de 2X1 de Cocteles', 'En margaritas, cosmopolitan y cuba libre', 15000)";

        basedatos.execSQL(insert1);
        basedatos.execSQL(insert2);
        basedatos.execSQL(insert3);
        basedatos.execSQL(insert4);
    }

    public Cursor listarPromociones()
    {
        SQLiteDatabase basedatos = this.getWritableDatabase();
        String show = "SELECT codigo as _id, titulo, descripcion, valor FROM promociones";

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
        db.execSQL("DROP TABLE IF EXISTS promociones");
        db.execSQL(bd);
    }
}
