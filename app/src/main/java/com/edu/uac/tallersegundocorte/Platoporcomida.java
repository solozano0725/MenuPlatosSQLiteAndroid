package com.edu.uac.tallersegundocorte;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sol Mayra on 06/05/2017.
 */

public class Platoporcomida  extends AppCompatActivity {

    SimpleCursorAdapter adaptador;
    Cursor cur;
    ListView lista;
    RadioGroup rdg_tcomida;
    RadioButton rb_col, rb_mex, rb_ita, rb_arab;
    TextView txtvwcodigo, txtvwnombre, txtvwdescripcion, txtvwtipoplato, txtvwtipocomida, txtvwprecio;
    Button btnbuscar;
    DBHelper bd = new DBHelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platoporcomida);

        rdg_tcomida = (RadioGroup) findViewById(R.id.radio_group_tcomida);

        rb_col = (RadioButton) findViewById(R.id.rad_btn_col);
        rb_mex = (RadioButton) findViewById(R.id.rad_btn_mex);
        rb_ita = (RadioButton) findViewById(R.id.rad_btn_ita);
        rb_arab = (RadioButton) findViewById(R.id.rad_btn_Arab);

        txtvwnombre = (TextView) findViewById(R.id.txtvwnombre);
        txtvwcodigo = (TextView) findViewById(R.id.txtvwcodigo);
        txtvwdescripcion = (TextView) findViewById(R.id.txtvwdescrip);
        txtvwtipoplato = (TextView) findViewById(R.id.txtvwtipoplato);
        txtvwtipocomida = (TextView) findViewById(R.id.txtvwtipocomida);
        txtvwprecio = (TextView) findViewById(R.id.txtvwprecio);

        btnbuscar = (Button) findViewById(R.id.btnbuscarcomida);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String col[] = new String[]{"_id","nombre","descripcion","tipoplato","tipocomida","precio"};
                String tc = tipoComida();
                int[] destino = new int[]{R.id.txtvwcodigo, R.id.txtvwnombre, R.id.txtvwdescrip, R.id.txtvwtipoplato, R.id.txtvwtipocomida ,R.id.txtvwprecio};
                cur = platosPorComida(tc);
                adaptador = new SimpleCursorAdapter(getApplicationContext(),R.layout.listadoplatos,cur,col,destino,0);
                lista = (ListView) findViewById(R.id.listViewComida);
                adaptador.notifyDataSetChanged();
                lista.setAdapter(adaptador);
            }
        });

    }

    public Cursor platosPorComida(String tc){

        Cursor c = bd.buscarPlato_portipodecomida(tc);
        if(c.moveToFirst())
        {
            Toast.makeText(this, "Busqueda completada!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "No hay platos con este tipo de comida.", Toast.LENGTH_SHORT).show();
        }

        return c;
    }

    public String tipoComida()
    {
        String tipocomida = "";

        if(rb_col.isChecked())
        {
            tipocomida = "Colombiana";
        }
        else
        if(rb_mex.isChecked())
        {
            tipocomida = "Mexicana";
        }
        else
        if(rb_ita.isChecked())
        {
            tipocomida = "Italiana";
        }else
        if(rb_arab.isChecked())
        {
            tipocomida = "Arabe";
        }
        return tipocomida;
    }

}
