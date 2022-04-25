package com.example.reminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.sql.Date;
import java.sql.Time;
import java.util.Locale;

public class NewActivity extends AppCompatActivity {



    EditText title,time,description;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new );
        getSupportActionBar().setTitle( " " );



        title = (EditText) findViewById( R.id.title );
        description = (EditText) findViewById( R.id.description );
        time = (EditText) findViewById( R.id.time );


    }

    private void SaveText(String data, String currentTime){



        SharedPreferences sharedPreferences = getSharedPreferences( "MySharedPref",MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "title",data );
        editor.putString( "description",data );
        editor.putString( "time",currentTime );



    }



    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu2, menu );
        return super.onCreateOptionsMenu( menu );
    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {

        Intent intent = new Intent(this, MainActivity.class);

        switch (item.getItemId()) {
            case R.id.return_item:
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}