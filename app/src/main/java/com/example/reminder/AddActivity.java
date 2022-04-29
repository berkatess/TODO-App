package com.example.reminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {


    Button save_button;
    EditText title,time,description;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add );




        title = (EditText) findViewById( R.id.title_input );
        description = (EditText) findViewById( R.id.description_input );
        time = (EditText) findViewById( R.id.time_input );
        save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                DatabaseHelper myDB = new DatabaseHelper( AddActivity.this);
                myDB.addBook(title.getText().toString().trim(),
                        description.getText().toString().trim(),
                        Integer.valueOf(time.getText().toString().trim()));
                Intent intent = new Intent(view.getRootView().getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        } );


    }


}