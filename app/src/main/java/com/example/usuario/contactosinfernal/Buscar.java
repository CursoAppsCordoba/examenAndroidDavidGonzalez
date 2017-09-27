package com.example.usuario.contactosinfernal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Comparator;

public class Buscar extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar);
        Button btnbuscar=(Button)findViewById(R.id.buscar);
        btnbuscar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            EditText buscador = (EditText) findViewById(R.id.lupa);
            String nombre = buscador.getText().toString();
            Contacto contacto2 = new Contacto(nombre);
            Intent intent = new Intent();
            intent.putExtra("busqueda", contacto2);
            setResult(Activity.RESULT_OK, intent);
            finish();

        }


}
