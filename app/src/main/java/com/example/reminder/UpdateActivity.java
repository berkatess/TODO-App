package com.example.reminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, description_input, times_input;
    Button update_button;

    String id, title, description, times;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        description_input = findViewById(R.id.description_input2);
        times_input = findViewById(R.id.times_input2);
        update_button = findViewById(R.id.update_button);

//        delete_button = findViewById(R.id.delete_button);


        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }


        update_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                DatabaseHelper myDatabase = new DatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                times = times_input.getText().toString().trim();
                myDatabase.updateData(id, title, description, times);
                Intent intent = new Intent(view.getRootView().getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        } );

//        delete_button.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick (View view) {
//                confirmDialog();
//            }
//        } );

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("times")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
            times = getIntent().getStringExtra("times");

            //Setting Intent Data
            title_input.setText(title);
            description_input.setText(description);
            times_input.setText(times);
            Log.d("stev", title+" "+description+" "+times);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

//    void confirmDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete " + title + " ?");
//        builder.setMessage("Are you sure you want to delete " + title + " ?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                DatabaseHelper myDatabase = new DatabaseHelper(UpdateActivity.this);
//                myDatabase.deleteOneRow(id);
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        builder.create().show();
//    }
}





