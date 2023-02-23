package com.example.recyclerviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleContactAdapter extends RecyclerView.Adapter<RecycleContactAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> arrContact;


    RecycleContactAdapter(Context context, ArrayList<ContactModel> arrContact) {
        this.context = context;
        this.arrContact = arrContact;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(arrContact.get(position).img);
        holder.txtname.setText(arrContact.get(position).name);
        holder.txtNumber.setText(arrContact.get(position).number);

    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtNumber;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.txtname);
            txtNumber = itemView.findViewById(R.id.txtnumber);
            img = itemView.findViewById(R.id.imgContact);

        }
    }
}
