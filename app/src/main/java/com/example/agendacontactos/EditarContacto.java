package com.example.agendacontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarContacto extends AppCompatActivity {
    EditText nombre, telefono, direccion, email;
    ArrayList<String> myLista =new ArrayList<String>();
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);
        setTitle("Editar Contacto");
        viewAsigned();
    }

    private void viewAsigned() {
        nombre=findViewById(R.id.ET_nombre);
        telefono=findViewById(R.id.ET_telefono);
        direccion=findViewById(R.id.ET_direccion);
        email=findViewById(R.id.ET_email);
        llenaCampos();//llamamos al metodo que permite llenar los campos con la informacion
    }

    private void llenaCampos(){//eset metodo se encargar de colocar la info del contacto para editarla
        final Intent intent = this.getIntent();//obtenemos el intent actual
        if(intent.hasExtra("lista") && intent.hasExtra("posicion"))//verificamos si en el intent actual existe un extras llama lista
        {
            extras=getIntent().getExtras();//obtenemos los parametros en extras
            myLista= extras.getStringArrayList("lista");//obtenemos la lista de arrays
            int posicion = extras.getInt("posicion");
            String[] fragmentos =myLista.get(posicion).split(",");
            nombre.setText(fragmentos[0]);//coemzamos a colocar los datos en los campos
            telefono.setText(fragmentos[1]);
            direccion.setText(fragmentos[2]);
            email.setText(fragmentos[3]);
        }
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
                int posicion=extras.getInt("posicion");
                myLista.set(posicion,ContactoNombre+","+ContactoTelefono+","+ContactoDireccion+","+ContactoEmail);//modificamos el contacto
                //indicamos la posicion que se modificara y el valor que lo sustituira
            }
            Toast.makeText(this, "Editado Exitosamente", Toast.LENGTH_SHORT).show();
            intentMain.putExtra("NuevaLista", myLista);
            startActivity(intentMain);
            finish();
        }
    }
}