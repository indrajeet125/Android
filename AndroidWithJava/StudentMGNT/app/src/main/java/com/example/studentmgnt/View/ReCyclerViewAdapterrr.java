package com.example.studentmgnt.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmgnt.R;
import com.example.studentmgnt.RecyCleStudents;
import com.example.studentmgnt.displayonestundentDetails;
import com.example.studentmgnt.model.Student;

import java.util.List;


public class ReCyclerViewAdapterrr extends RecyclerView.Adapter<ReCyclerViewAdapterrr.ViewHolder> {

    Context context;
    List<Student> studentList;

    public ReCyclerViewAdapterrr(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;

    }

    @NonNull
    @Override
    public ReCyclerViewAdapterrr.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onestudentforrecycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReCyclerViewAdapterrr.ViewHolder holder, int position) {
        Student student = studentList.get(position);

        holder.rID.setText(student.getSch_id() + "");
        holder.rName.setText(student.getName());


    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rID, rName;
        Button detailsButoon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rID = itemView.findViewById(R.id.s1Id);
            rName = itemView.findViewById(R.id.s1name);
            detailsButoon = itemView.findViewById(R.id.btnDetails);
            detailsButoon.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int pos = this.getAdapterPosition();
            Student student = studentList.get(pos);
            System.out.println(pos);

            Intent intent = new Intent(context, displayonestundentDetails.class);
            System.out.println(student);

            intent.putExtra("id", student.getSch_id()+"");
            intent.putExtra("name", student.getName());
            intent.putExtra("gender", student.getGender());
            intent.putExtra("mobile", student.getMobile());
            intent.putExtra("email", student.getEmail());
            intent.putExtra("district", student.getDistrict());
            intent.putExtra("state", student.getState());




            context.startActivity(intent);


        }
    }
}

