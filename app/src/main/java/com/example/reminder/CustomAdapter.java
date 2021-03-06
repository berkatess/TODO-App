package com.example.reminder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList reminder_id, reminder_title, reminder_description, reminder_times;

    CustomAdapter(Activity activity, Context context, ArrayList reminder_id, ArrayList reminder_title, ArrayList reminder_description,
                  ArrayList reminder_times){
        this.activity = activity;
        this.context = context;
        this.reminder_id = reminder_id;
        this.reminder_title = reminder_title;
        this.reminder_description = reminder_description;
        this.reminder_times = reminder_times;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.reminder_title_txt.setText(String.valueOf(reminder_title.get(position)));
        holder.reminder_description_txt.setText(String.valueOf(reminder_description.get(position)));
        holder.reminder_times_txt.setText(String.valueOf(reminder_times.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder( view.getRootView().getContext());
                builder.setPositiveButton( "Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, UpdateActivity.class);
                        intent.putExtra("id", String.valueOf(reminder_id.get(position)));
                        intent.putExtra("title", String.valueOf(reminder_title.get(position)));
                        intent.putExtra("description", String.valueOf(reminder_description.get(position)));
                        intent.putExtra("times", String.valueOf(reminder_times.get(position)));
                        activity.startActivityForResult(intent, 1);
                    }
                } );
                builder.setNegativeButton( "Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialogInterface, int i) {


                        DatabaseHelper myDatabase = new DatabaseHelper(view.getRootView().getContext());
                        myDatabase.deleteOneRow( String.valueOf( reminder_id.get( position ) ) );
                        Intent intent = new Intent(view.getRootView().getContext(), MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }

                } );
                builder.create().show();



            }
        });
    }

    @Override
    public int getItemCount () {
        return reminder_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView reminder_title_txt, reminder_description_txt, reminder_times_txt;
        LinearLayout mainLayout;

        public MyViewHolder (@NonNull View itemView) {
            super( itemView );

            reminder_title_txt = itemView.findViewById(R.id.reminder_title_txt);
            reminder_description_txt = itemView.findViewById(R.id.reminder_description_txt);
            reminder_times_txt = itemView.findViewById(R.id.reminder_times_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }






        }





