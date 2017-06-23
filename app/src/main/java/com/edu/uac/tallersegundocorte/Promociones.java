package com.edu.uac.tallersegundocorte;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Sol Mayra on 06/05/2017.
 */

public class Promociones  extends AppCompatActivity {

    Button btnpromocion;
    SimpleCursorAdapter adaptador;
    Cursor cur;
    ListView lista;
    TextView txttitulo, txtdescripcion, txtprecio, txtcodigo;
    DBPromociones db = new DBPromociones(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);
        btnpromocion = (Button) findViewById(R.id.btnpromocion);

        lista = (ListView) findViewById(R.id.listViewPromocion);
        txtcodigo = (TextView) findViewById(R.id.txtcodigo);
        txttitulo = (TextView) findViewById(R.id.txttitulo);
        txtdescripcion = (TextView) findViewById(R.id.txtdescripcion);
        txtprecio = (TextView) findViewById(R.id.txtprecio);

        btnpromocion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.agregarPlato();
                String col[] = new String[]{"_id","titulo", "descripcion", "valor"};
                int[] destino = new int[]{R.id.txtcodigo, R.id.txttitulo,R.id.txtdescripcion, R.id.txtprecio};
                cur = ListarPromociones();
                adaptador = new SimpleCursorAdapter(getApplicationContext(),R.layout.listadopromocion,cur,col,destino,0);
                lista = (ListView) findViewById(R.id.listViewPromocion);
                adaptador.notifyDataSetChanged();
                lista.setAdapter(adaptador);
            }
        });

    }

    public Cursor ListarPromociones()
    {
        Cursor c = db.listarPromociones();

        if (c!=null)
        {
            c.moveToFirst();
        }

        db.close();
        return c;
    }
}
