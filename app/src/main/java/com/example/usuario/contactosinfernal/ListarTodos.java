package com.example.usuario.contactosinfernal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarTodos extends AppCompatActivity {

    public ListView lwlistacontac;
    public ArrayList<Contacto> listacontac;
    public ArrayList<String> lista;
    public ArrayAdapter adaptador;
    public ArrayAdapter adaptador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_todos);
        final Button btnedit=(Button)findViewById(R.id.bteditar);
        Button btncancel=(Button)findViewById(R.id.btncancel);


        lwlistacontac=(ListView)findViewById(R.id.listac);
        Intent intent = getIntent();
        listacontac = new ArrayList<Contacto>();
        lista = new ArrayList<String>();
        listacontac=(ArrayList)intent.getParcelableArrayListExtra("lista");

        adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listacontac);


        lwlistacontac.setAdapter(adaptador2);
        lwlistacontac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListarTodos.this);
                dialogo1.setTitle("Atención");
                dialogo1.setMessage("¿ Editar este contacto ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {


                        EditText enombre=(EditText)findViewById(R.id.editarnombre);
                        enombre.setText(listacontac.get(i).getNombre());
                        EditText email=(EditText)findViewById(R.id.editaremail);
                        email.setText(listacontac.get(i).getEmail());
                        EditText ededad=(EditText)findViewById(R.id.editaredad);
                        ededad.setText(listacontac.get(i).getEdad());
                        setContentView(R.layout.editar);
                        Button xxx=(Button)findViewById(R.id.btncancel);
                        xxx.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        /*listacontac.get(i).setNombre(enombre.getText().toString());
                        listacontac.get(i).setEmail(email.getText().toString());
                        listacontac.get(i).setEdad(Integer.parseInt(ededad.getText().toString()));*/


                        adaptador2.notifyDataSetChanged();



                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

            }
        });
    }


}
