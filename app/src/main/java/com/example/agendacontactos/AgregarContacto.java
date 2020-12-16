package com.example.agendacontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class AgregarContacto extends AppCompatActivity {

    EditText nombre, telefono, direccion, email;
    ArrayList<Contacto> listaContactos =new ArrayList<Contacto>();
    ArrayList<String> myLista =new ArrayList<String>();
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setTitle("Agregar Contacto");
        setContentView(R.layout.activity_agregar_contacto);
        asiganciones();
    }

    private void asiganciones() {
        nombre=findViewById(R.id.ET_nombre);
        telefono=findViewById(R.id.ET_telefono);
        direccion=findViewById(R.id.ET_direccion);
        email=findViewById(R.id.ET_email);
    }

    public void descarta(View view) {
        Intent intentAllcontactos = new Intent(this, MainActivity.class);
        final Intent intent = this.getIntent();//obtenemos el intent actual
        if(intent.hasExtra("lista"))//verificamos si en el intent actual existe un extras llama lista
        {
            extras=getIntent().getExtras();//obtenemos los parametros en extras
            myLista= extras.getStringArrayList("lista");//obtenemos la lista de arrays
            intentAllcontactos.putExtra("NuevaLista",myLista);//al cancelar la opcion de
            //registarr un contacto, tertonamos la lista que se envia a este activity para que se mantengan
            //los contactos
        }
        startActivity(intentAllcontactos);//invoco al activity main

    }

    public void guardar(View view) {
    String ContactoNombre=nombre.getText().toString(), ContactoTelefono=telefono.getText().toString(),
            ContactoDireccion=direccion.getText().toString(), ContactoEmail=email.getText().toString();
        if(!ContactoNombre.isEmpty()&& !ContactoTelefono.isEmpty() && !ContactoDireccion.isEmpty()
                && !ContactoTelefono.isEmpty()){

            Intent intentMain = new Intent(this, MainActivity.class);
            final Intent intent = this.getIntent();//obtenemos el intent actual
           if(intent.hasExtra("lista"))//verificamos si en el intent actual existe un extras llama lista
           {
               extras=getIntent().getExtras();//obtenemos los parametros en extras
               myLista= extras.getStringArrayList("lista");//obtenemos la lista de arrays
               myLista.add(ContactoNombre+","+ContactoTelefono+","+ContactoDireccion+","+ContactoEmail);//añadimos un nuevo item
               Log.i("solucion2", "El tamaño de la lista nuevo es "+myLista.size());
           }
           else{
               myLista.add(ContactoNombre+","+ContactoTelefono+","+ContactoDireccion+","+ContactoEmail);
               Log.i("solucion3", "El tamaño de la lista nuevo es "+myLista.size());
           }
            intentMain.putExtra("NuevaLista", myLista);
            startActivity(intentMain);
            finish();
        }
    }
}