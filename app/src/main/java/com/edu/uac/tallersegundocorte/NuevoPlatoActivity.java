package com.edu.uac.tallersegundocorte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class NuevoPlatoActivity extends AppCompatActivity {

    EditText edt_codigo, edt_nombre, edt_descripcion, edt_valor;
    RadioGroup rdg_tplato, rdg_tcomida;
    Button btnagregarpl;
    RadioButton radio_pf, radio_bebida, radio_col, radio_mex, radio_ita, radio_arab;
    DBHelper bd = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);

        edt_codigo = (EditText) findViewById(R.id.edit_codigoplato);
        edt_nombre = (EditText) findViewById(R.id.edit_nombreplato);
        edt_descripcion = (EditText) findViewById(R.id.edit_descripcion);
        edt_valor = (EditText) findViewById(R.id.edit_valorplato);

        btnagregarpl = (Button) findViewById(R.id.btn_agregarplato);

        rdg_tplato = (RadioGroup) findViewById(R.id.radio_group_tplato);
        rdg_tcomida = (RadioGroup) findViewById(R.id.radio_group_tcomida);

        radio_pf = (RadioButton)findViewById(R.id.rad_btn_pf);
        radio_bebida = (RadioButton)findViewById(R.id.radiobn_bebida);
        radio_col = (RadioButton)findViewById(R.id.rad_btn_col);
        radio_mex = (RadioButton)findViewById(R.id.rad_btn_mex);
        radio_ita = (RadioButton)findViewById(R.id.rad_btn_ita);
        radio_arab = (RadioButton)findViewById(R.id.rad_btn_Arab);

        btnagregarpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarNuevoPlato();
                limpiarEntrada();
            }
        });

    }



    public void limpiarEntrada()
    {
        edt_codigo.setText(null);
        edt_nombre.setText(null);
        edt_descripcion.setText(null);
        edt_valor.setText(null);
        rdg_tplato.clearCheck();
        rdg_tcomida.clearCheck();
    }

    public String tipoPlato()
    {
        String tipoplato = "";

        if(radio_pf.isChecked())
        {
            tipoplato = "Plato Fuerte";
        }
        else
        if(radio_bebida.isChecked())
        {
            tipoplato = "Bebida";
        }

        return tipoplato;
    }

    public String tipoComida()
    {
        String tipocomida = "";

        if(radio_col.isChecked())
        {
            tipocomida = "Colombiana";
        }
        else
        if(radio_mex.isChecked())
        {
            tipocomida = "Mexicana";
        }
        else
        if(radio_ita.isChecked())
        {
            tipocomida = "Italiana";
        }else
        if(radio_arab.isChecked())
        {
            tipocomida = "Arabe";
        }
        return tipocomida;
    }


        public void agregarNuevoPlato()
        {
            int codigo = Integer.parseInt(edt_codigo.getText().toString());
            String nombre = edt_nombre.getText().toString();
            String descripcion = edt_descripcion.getText().toString();
            String tipoplato = tipoPlato();
            String tipocomida = tipoComida();
            int precio = Integer.parseInt(edt_valor.getText().toString());


            if (bd.buscarPlato_porcodigo(codigo).getCount() == 0) {
                bd.agregarPlato(codigo, nombre, descripcion, tipoplato, tipocomida, precio);
                bd.close();
                Toast.makeText(this, "Registro completado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El codigo ya existe!", Toast.LENGTH_SHORT).show();
            }
        }

}





