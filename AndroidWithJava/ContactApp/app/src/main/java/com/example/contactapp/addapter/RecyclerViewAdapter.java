package com.example.contactapp.addapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp.R;
import com.example.contactapp.displayContact;
import com.example.contactapp.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;


    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    //where to get the single card as viewHolder object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    // what will happen after we create the viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Icon icon;
        Contact contact = contactList.get(position);
        holder.contactname.setText(contact.getName());
        holder.phonenuber.setText(contact.getPhoneNumber());

    }

    //how many items
    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView contactname;
        public TextView phonenuber;
        public ImageView iconbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            contactname = itemView.findViewById(R.id.name);
            phonenuber = itemView.findViewById(R.id.phone);
            iconbutton = itemView.findViewById(R.id.image);

//            iconbutton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int possion = this.getAdapterPosition();
            Contact contact = contactList.get(possion);

            String name = contact.getName();
            String phone = contact.getPhoneNumber();

            Intent intent =new Intent(context, displayContact.class);
            intent.putExtra("name",name);
            intent.putExtra("phone",phone);
            context.startActivities(new Intent[]{intent});

        }
    }
}
