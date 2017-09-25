package com.example.usuario.contactosinfernal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int alta = 100;
    public static final int listar=101;
    public static final int buscar=102;
    public static final int eliminar=103;
    public ArrayList<Contacto> listacontacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnadd=(Button)findViewById(R.id.botonmas);
        btnadd.setOnClickListener(this);
        Button btnlook=(Button)findViewById(R.id.botonbuscar);
        btnlook.setOnClickListener(this);
        Button btnremove=(Button)findViewById(R.id.eliminar);
        btnremove.setOnClickListener(this);
        listacontacto=new ArrayList<Contacto>();
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        int codigo = 0;
        switch (v.getId()){
            case R.id.botonmas:
                intent=new Intent(this, Mascontacto.class);
                codigo=alta;
                break;
            case R.id.botonbuscar:
                intent=new Intent(this, Buscar.class);
               codigo=buscar;
                break;
            case R.id.botontodos:
                codigo=listar;
                intent=new Intent(this, ListarTodos.class);
                intent.putParcelableArrayListExtra("lista", listacontacto );
                break;
            case R.id.eliminar:
                codigo=eliminar;
                intent=new Intent(this, Buscar.class);
                break;
        }
        startActivityForResult(intent, codigo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText numcontact=(EditText)findViewById(R.id.contactosguardados);
        if (alta== requestCode){
            if (resultCode== Activity.RESULT_OK){
                if (data.hasExtra("contacto")){
                    listacontacto.add((Contacto)data.getParcelableExtra("contacto"));
                }

            }

            numcontact.setText("Contactos guardados "+listacontacto.size());
        }
       else if (buscar==requestCode){
            setContentView(R.layout.resultadobusqueda);
            EditText resbusq=(EditText)findViewById(R.id.resultadobusquedatxt);

            if (resultCode== Activity.RESULT_OK){
                if (data.hasExtra("busqueda")){
                    Contacto aux=data.getParcelableExtra("busqueda");
                    int encontrados=0;
                    for (Contacto c1:listacontacto) {
                       if (c1.getNombre().equals(aux.getNombre())){
                            encontrados++;
                           resbusq.setText("Contactos encontrados " + encontrados+'\''+ c1.toString());

                        }

                    }

                }

            }

        }else if (eliminar==requestCode){
            if (resultCode== Activity.RESULT_OK){
                if (data.hasExtra("busqueda")){
                    Contacto aux=data.getParcelableExtra("busqueda");
                    int encontrados=0;
                    for (Contacto c1:listacontacto) {
                        if (c1.getNombre().equals(aux.getNombre())){
                            encontrados++;
                            listacontacto.remove(c1);
                            numcontact.setText("Contactos guardados "+listacontacto.size());

                        }

                    }

                }

            }

        }


    }
}
