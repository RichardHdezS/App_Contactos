package com.example.agendacontactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements itemListener{
    RecyclerView cRecyclerView;
    ContactoAdapter adapter;
    ArrayList<Contacto> listaContactos =new ArrayList<Contacto>();
    ArrayList<String> myLista =new ArrayList<String>();
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cRecyclerView.setLayoutManager(linearLayoutManager);

        //pintamos una linea divisora entre cada item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        cRecyclerView.addItemDecoration(dividerItemDecoration);

        cRecyclerView.setHasFixedSize(true);//permitimos que todos los items sean del mismo tamaño
        adapter=new ContactoAdapter(crearContacto(), this, this);
        cRecyclerView.setAdapter(adapter);
    }

    public List<Contacto> crearContacto(){

        final Intent intent = this.getIntent();//obtenemos el intent actual
        if(intent.hasExtra("NuevaLista"))
        {
            extras = this.getIntent().getExtras();
            myLista=extras.getStringArrayList("NuevaLista");
            Log.i("solucion", "El tamaño de la lista en Main es "+myLista.size());
            for(int i=0; i<myLista.size(); i++){
                String[] fragmentos =myLista.get(i).split(",");
                listaContactos.add(new Contacto(fragmentos[0], fragmentos[1],
                        fragmentos[2], fragmentos[3]));
            }
            return listaContactos;
        }
        else{
            return listaContactos;
        }

    }

    //importamos un menu en la barra superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnAdd:
                Intent intentNuevoContacto = new Intent(this, AgregarContacto.class);
                intentNuevoContacto.putExtra("lista", myLista);
                startActivity(intentNuevoContacto);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int posicion, char accion) {
        if(accion == 'E'){
            Toast.makeText(this, "Posicion Editada "+posicion, Toast.LENGTH_SHORT).show();
            Log.i("lucha", myLista.get(posicion));
        }
        else {
            Toast.makeText(this, "Posicion Eliminada"+posicion, Toast.LENGTH_SHORT).show();
        }
    }
}