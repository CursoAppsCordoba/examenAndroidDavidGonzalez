package com.example.usuario.contactosinfernal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarTodos extends AppCompatActivity {

    public ListView lwlistacontac;
    public ArrayList<Contacto> listacontac;
    public ArrayList<String> lista;
    public ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_todos);
        Intent intent = getIntent();
        listacontac=(ArrayList)intent.getParcelableArrayListExtra("lista");
        for (Contacto c:listacontac) {
            lista.add(c.getNombre());
        }
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);

        lwlistacontac.setAdapter(adaptador);



    }
}
