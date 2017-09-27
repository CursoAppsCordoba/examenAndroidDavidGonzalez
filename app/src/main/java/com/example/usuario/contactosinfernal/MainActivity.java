package com.example.usuario.contactosinfernal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int ACTION_ALTA = 100;
    public static final int ACTION_LISTAR=101;
    public static final int ACTION_BUSCAR=102;
    public static final int ACTION_ELIMINAR=103;
    public static ArrayList<Contacto> listacontacto;
    //public Set<Contacto> lista;


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
                codigo=ACTION_ALTA;
                break;
            case R.id.botonbuscar:
                intent=new Intent(this, Buscar.class);
               codigo=ACTION_BUSCAR;
                break;
            case R.id.botontodos:
                codigo=ACTION_LISTAR;
                intent=new Intent(this, ListarTodos.class);
                intent.putParcelableArrayListExtra("lista", listacontacto );
                break;
            case R.id.eliminar:
                codigo=ACTION_ELIMINAR;
                intent=new Intent(this, Buscar.class);
                break;
        }
        startActivityForResult(intent, codigo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText numcontact=(EditText)findViewById(R.id.contactosguardados);
        if (ACTION_ALTA== requestCode){
            if (resultCode== Activity.RESULT_OK){
                if (data.hasExtra("contacto")){
                    listacontacto.add((Contacto)data.getParcelableExtra("contacto"));
                    //lista=new HashSet<>(listacontacto);

                }

            }

            numcontact.setText("Contactos:"+listacontacto.size());
        }
       else if (ACTION_BUSCAR==requestCode){
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

        }else if (ACTION_ELIMINAR==requestCode){
            if (resultCode== Activity.RESULT_OK){
                if (data.hasExtra("busqueda")){
                    Contacto aux=data.getParcelableExtra("busqueda");
                    int encontrados=0;
                    for (Contacto c1:listacontacto) {
                        if (c1.getNombre().equals(aux.getNombre())){
                            encontrados++;
                            listacontacto.remove(c1);
                            //lista=new HashSet<>(listacontacto);
                            numcontact.setText("Contactos:"+listacontacto.size());

                        }

                    }

                }

            }

        }else if (ACTION_LISTAR==requestCode){
            if (data.hasExtra("listado")){
                listacontacto=data.getParcelableArrayListExtra("listado");
                //lista=new HashSet<>(listacontacto);
                numcontact.setText("Contactos:"+listacontacto.size());
            }
        }


    }
}
