package com.edu.uac.tallersegundocorte;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class VerPlatosActivity extends AppCompatActivity {

    SimpleCursorAdapter adaptador;
    Cursor cur;
    ListView lista;

    DBHelper bd = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_platos);

        String col[] = new String[]{"_id","nombre","descripcion","tipoplato","tipocomida","precio"};
        int[] destino = new int[]{R.id.txtvwcodigo, R.id.txtvwnombre, R.id.txtvwdescrip, R.id.txtvwtipoplato, R.id.txtvwtipocomida ,R.id.txtvwprecio};
        cur = listplatos();
        adaptador = new SimpleCursorAdapter(getApplicationContext(),R.layout.listadoplatos,cur,col,destino,0);
        lista = (ListView) findViewById(R.id.listView);
        adaptador.notifyDataSetChanged();
        lista.setAdapter(adaptador);
    }

    public Cursor listplatos()
    {
        Cursor c = bd.listarplatos();

        if (c!=null)
        {
            c.moveToFirst();
        }

        bd.close();
        return c;
    }
}
