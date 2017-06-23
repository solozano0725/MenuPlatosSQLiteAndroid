package com.edu.uac.tallersegundocorte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_nuevoplato, btn_eliminarplato, btn_modificarplato, btn_consultarplato, btn_acerca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_nuevoplato = (Button) findViewById(R.id.btn_nuevoplato);
        btn_eliminarplato = (Button) findViewById(R.id.btn_eliminarplato);
        btn_modificarplato = (Button) findViewById(R.id.btn_modificarplato);
        btn_consultarplato = (Button) findViewById(R.id.btn_consultarplato);
        btn_acerca = (Button) findViewById(R.id.btn_acerca);

        btn_nuevoplato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirActivity(NuevoPlatoActivity.class);
            }
        });

        btn_eliminarplato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirActivity(EliminarPlatoActivity.class);
            }
        });

        btn_modificarplato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirActivity(ModificarPlatoActivity.class);
            }
        });

        btn_consultarplato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirActivity(ConsultarPlatoActivity.class);
            }
        });

        btn_acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirActivity(AcercaActivity.class);
            }
        });
    }

    public void AbrirActivity(Class activity)
    {
        Intent i=new Intent(this, activity);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ver_platos:
                AbrirActivity(VerPlatosActivity.class);
                return true;
            case R.id.promociones:
                AbrirActivity(Promociones.class);
                return true;
            case R.id.ver_plato_por_comida:
                AbrirActivity(Platoporcomida.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
