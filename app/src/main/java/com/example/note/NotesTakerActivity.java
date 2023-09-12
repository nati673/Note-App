package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.note.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
   EditText editText_Title,editText_notes;
   ImageView imageView_save;
   Notes notes;
   boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notes_taker );

        imageView_save = findViewById( R.id.imageView_save );
        editText_Title = findViewById( R.id.editText_Title );
        editText_notes = findViewById( R.id.editText_notes );

            notes = new Notes();
            try {
                notes = (Notes) getIntent().getSerializableExtra( "old_note" );
                editText_Title.setText( notes.getTitle());
                editText_notes.setText( notes.getNotes());
                isOldNote = true;
            }catch (Exception e){
                e.printStackTrace();
            }


        imageView_save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText_Title.getText().toString();

                String description = editText_notes.getText().toString();

            if (description.isEmpty()){
                Toast.makeText( NotesTakerActivity.this, "Please add some notes!", Toast.LENGTH_SHORT ).show();
                return;
            }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d  MMM yyy HH:mm: a ");
                 Date data = new Date();

             if (!isOldNote){
                 notes = new Notes();
             }

             notes.setNotes( description );
             notes.setTitle( title );
             notes.setDate(formatter.format(data));

                Intent intent = new Intent();
                intent.putExtra( "note", notes );
                setResult( Activity.RESULT_OK, intent );
                finish();
            }
        } );
    }
}