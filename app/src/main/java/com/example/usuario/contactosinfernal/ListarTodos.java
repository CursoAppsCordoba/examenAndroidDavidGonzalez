package com.example.usuario.contactosinfernal;

import android.app.Activity;
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
    public ArrayAdapter adaptador2;
    public EditText enombre;
    public EditText email;
    public EditText ededad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_todos);

        Intent intent = getIntent();
       listacontac = new ArrayList<Contacto>();

        listacontac=(ArrayList)intent.getParcelableArrayListExtra("lista");
        lwlistacontac=(ListView)findViewById(R.id.listac);

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

                        setContentView(R.layout.editar);
                        enombre=(EditText)findViewById(R.id.editarnombre);
                        enombre.setText(listacontac.get(i).getNombre());
                        email=(EditText)findViewById(R.id.editaremail);
                        email.setText(listacontac.get(i).getEmail());
                        ededad=(EditText)findViewById(R.id.editaredad);

                        Button bedit=(Button)findViewById(R.id.bteditar);
                        bedit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                        listacontac.get(i).setNombre(enombre.getText().toString());
                                listacontac.get(i).setEmail(email.getText().toString());
                                listacontac.get(i).setEdad(Integer.parseInt(ededad.getText().toString()));
                                Intent intent= new Intent();
                                intent.putParcelableArrayListExtra("listado", listacontac);
                                setResult(Activity.RESULT_OK, intent);
                                finish();

                                //setContentView(R.layout.activity_listar_todos);
                                //adaptador2.notifyDataSetChanged();

                            }
                        });
                        Button bcancel=(Button)findViewById(R.id.btncancel);
                        bcancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent();
                                setResult(Activity.RESULT_OK, intent);
                                finish();
                            }
                        });

                        /*listacontac.get(i).setNombre(enombre.getText().toString());
                        listacontac.get(i).setEmail(email.getText().toString());
                        listacontac.get(i).setEdad(Integer.parseInt(ededad.getText().toString()));*/


                       // adaptador2.notifyDataSetChanged();



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
