package com.edu.uac.tallersegundocorte;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultarPlatoActivity extends AppCompatActivity {

    EditText edt_nombre;
    TextView cod, desc, tplato, tcomida, precio;
    Button btn_Buscar;

    DBHelper bd = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_plato);

        edt_nombre = (EditText) findViewById(R.id.edt_nombrepla);
        cod = (TextView) findViewById(R.id.txtvwcodigo);
        desc = (TextView) findViewById(R.id.txtvwdescrip);
        tplato = (TextView) findViewById(R.id.txtvwtipoplato);
        tcomida = (TextView) findViewById(R.id.txtvwtipocomida);
        precio = (TextView) findViewById(R.id.txtvwprecio);
        btn_Buscar = (Button) findViewById(R.id.btn_buscarplato);

        btn_Buscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Buscar();
            }

        });


    }

    public void limpiarEntrada() {
        edt_nombre.setText(null);
        cod.setText(null);
        desc.setText(null);
        tplato.setText(null);
        tcomida.setText(null);
        precio.setText(null);
    }

    public void Buscar() {
        String nombre = edt_nombre.getText().toString();

        Cursor c = bd.buscarPlato_pornombre(nombre);

        if (c.moveToFirst()) {
            cod.setText(String.valueOf(c.getInt(0)));
            desc.setText(c.getString(2));
            tplato.setText(c.getString(3));
            tcomida.setText(c.getString(4));
            precio.setText(String.valueOf(c.getInt(5)));

            Toast.makeText(this, "Busqueda completada!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "El plato no existe en la base de datos!", Toast.LENGTH_SHORT).show();
            limpiarEntrada();
        }
    }

}
