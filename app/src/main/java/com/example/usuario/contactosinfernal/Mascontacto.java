package com.example.usuario.contactosinfernal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Mascontacto extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mascontacto);
        Button btn1=(Button)findViewById(R.id.botonalta);
        btn1.setOnClickListener(this);
        Button btn2=(Button)findViewById(R.id.cancelaradd);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.botonalta:
                AlertDialog.Builder cuadrodesalida = new AlertDialog.Builder(this);
                cuadrodesalida.setTitle("¿Seguro de añadir contacto?");
                cuadrodesalida.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText textnombre=(EditText)findViewById(R.id.nombre);
                        EditText textmail=(EditText)findViewById(R.id.email);
                        EditText textedad=(EditText)findViewById(R.id.edad);
                        Contacto contacto1= new Contacto(textnombre.getText().toString(),textmail.getText().toString(),Integer.parseInt(textedad.getText().toString()));
                        Intent intent= new Intent();
                        intent.putExtra("contacto", contacto1);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                });
                cuadrodesalida.setNegativeButton("NO", null);

                cuadrodesalida.show();


                break;
            case R.id.cancelaradd:
                finish();
                break;
        }


    }
}

