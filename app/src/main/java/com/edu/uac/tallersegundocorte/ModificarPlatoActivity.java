package com.edu.uac.tallersegundocorte;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarPlatoActivity extends AppCompatActivity {

    EditText edt_nombre, edt_codigo, edt_descrip, edt_precio;
    TextView txtvwnombre, txtvwcodigo, txtvwdescripcion, txtvwtipoplato, txtvwtipocomida, txtvwprecio;
    Button btnbuscar, btnmodificar;
    RadioGroup rdg_tplato, rdg_tcomida;
    RadioButton rb_platofuerte, rb_bebida, rb_col, rb_mex, rb_ita, rb_arab;
    DBHelper bd = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_plato);

        edt_codigo = (EditText) findViewById(R.id.codigoplato);
        edt_nombre = (EditText) findViewById(R.id.edit_nomplato);
        edt_descrip = (EditText) findViewById(R.id.edit_descrip) ;
        edt_precio= (EditText) findViewById(R.id.edit_preciomodificar);

        txtvwnombre = (TextView) findViewById(R.id.txtvwnombre);
        txtvwcodigo = (TextView) findViewById(R.id.txtvwcodigo);
        txtvwdescripcion = (TextView) findViewById(R.id.txtvwdescrip);
        txtvwtipoplato = (TextView) findViewById(R.id.txtvwtipoplato);
        txtvwtipocomida = (TextView) findViewById(R.id.txtvwtipocomida);
        txtvwprecio = (TextView) findViewById(R.id.txtvwprecio);

        rdg_tplato = (RadioGroup) findViewById(R.id.radio_group_tplato);
        rdg_tcomida = (RadioGroup) findViewById(R.id.radio_group_tcomida);

        rb_platofuerte = (RadioButton) findViewById(R.id.rad_btn_pf);
        rb_bebida = (RadioButton) findViewById(R.id.radiobn_bebida);
        rb_col = (RadioButton) findViewById(R.id.rad_btn_col);
        rb_mex = (RadioButton) findViewById(R.id.rad_btn_mex);
        rb_ita = (RadioButton) findViewById(R.id.rad_btn_ita);
        rb_arab = (RadioButton) findViewById(R.id.rad_btn_Arab);

        btnbuscar= (Button) findViewById(R.id.btnbuscar);
        btnmodificar= (Button) findViewById(R.id.btnmodificar);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar();
                renovar();
            }
        });
    }

    public void limpiarEntrada()
    {
        edt_codigo.setText(null);
        txtvwcodigo.setText(null);
        txtvwnombre.setText(null);
        txtvwdescripcion.setText(null);
        txtvwtipocomida.setText(null);
        txtvwtipoplato.setText(null);
        txtvwprecio.setText(null);
    }

    public void limpiar2(){

        edt_nombre.setText(null);
        edt_descrip.setText(null);
        rdg_tplato.clearCheck();
        rdg_tcomida.clearCheck();
        edt_precio.setText(null);

    }


    public void Buscar()
    {
        int codigo = Integer.parseInt(edt_codigo.getText().toString());

        Cursor c = bd.buscarPlato_porcodigo(codigo);

        if(c.moveToFirst())
        {
            txtvwcodigo.setText(String.valueOf(c.getInt(0)));
            txtvwnombre.setText(c.getString(1));
            txtvwdescripcion.setText(c.getString(2));
            txtvwtipoplato.setText(c.getString(3));
            txtvwtipocomida.setText(c.getString(4));
            txtvwprecio.setText(String.valueOf(c.getInt(5)));

            Toast.makeText(this, "Busqueda completada!", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, "El plato no existe en la base de datos!", Toast.LENGTH_SHORT).show();
            limpiarEntrada();
        }
    }


    public String tipoPlato()
    {
        String tipoplato = "";

        if(rb_platofuerte.isChecked())
        {
            tipoplato = "Plato Fuerte";
        }
        else
        if(rb_bebida.isChecked())
        {
            tipoplato = "Bebida";
        }

        return tipoplato;
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

    public void Modificar()
    {
        int codigo = Integer.parseInt(edt_codigo.getText().toString());
        String nombre = edt_nombre.getText().toString();
        String descripcion = edt_descrip.getText().toString();
        int precio = Integer.parseInt(edt_precio.getText().toString());
        String tipoplato = tipoPlato();
        String tipocomida = tipoComida();

        bd.modificarPlato(codigo, nombre, descripcion, tipoplato, tipocomida, precio);
        bd.close();

        Toast.makeText(this, "Modificacion exitosa!", Toast.LENGTH_SHORT).show();
        limpiar2();

    }

    public void renovar()
    {
        int codigo = Integer.parseInt(edt_codigo.getText().toString());

        Cursor c = bd.buscarPlato_porcodigo(codigo);

        if(c.moveToFirst())
        {
            txtvwcodigo.setText(String.valueOf(c.getInt(0)));
            txtvwnombre.setText(c.getString(1));
            txtvwdescripcion.setText(c.getString(2));
            txtvwtipoplato.setText(c.getString(3));
            txtvwtipocomida.setText(c.getString(4));
            txtvwprecio.setText(String.valueOf(c.getInt(5)));
            edt_codigo.setText(null);
        }
        else
        {
            Toast.makeText(this, "El Plato no existe!", Toast.LENGTH_SHORT).show();
            limpiarEntrada();
        }
    }
}
