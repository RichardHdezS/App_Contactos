package com.example.agendacontactos;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ViewHolder>{
    List<Contacto> listaContactos;
    Context context;
    itemListener itemListener;
    public ContactoAdapter(List<Contacto> listaContactos, Context context, itemListener itemListener) {
        this.listaContactos = listaContactos;
        this.context = context;
        this.itemListener=itemListener;
    }

    @NonNull
    @Override
    public ContactoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_contacto, parent, false);
            //ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view, itemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactoAdapter.ViewHolder holder, final int position) {
        Contacto contac = listaContactos.get(position);
        holder.nombre.setText(contac.getNombre());
        holder.telefono.setText(contac.getTelefono());
        holder.direccion.setText(contac.getDireccion());
        holder.email.setText(contac.getEmail());

        holder.picEdit.setTag("editado");
        holder.picDelete.setTag("eliminado");
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();//cantidad de objetos de la lista retornados
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, telefono, direccion, email;
        ImageView picContact, picDelete, picEdit;
        public ViewHolder(@NonNull View itemView, final itemListener itemListener) {
            super(itemView);
            nombre=itemView.findViewById(R.id.tvC_nombre);
            telefono=itemView.findViewById(R.id.tvC_telefono);
            direccion=itemView.findViewById(R.id.tvC_direccion);
            email=itemView.findViewById(R.id.tvC_email);
            picContact=itemView.findViewById(R.id.img_Contacto);
            picEdit=itemView.findViewById(R.id.img_EditContact);
            picDelete=itemView.findViewById(R.id.img_DeleteContact);
            itemView.findViewById(R.id.img_DeleteContact).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onClick(getAdapterPosition(), 'D');
                }
            });
            itemView.findViewById(R.id.img_EditContact).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onClick(getAdapterPosition(), 'E');
                }
            });
        }
    }
}
