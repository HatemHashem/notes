package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

public class AddNoteActivity extends AppCompatActivity {
    private EditText noteTitleEditText;
    private EditText noteDescriptionEditText;
    private NumberPicker noteNumberPicker;
    public static final String EXTRA_TITLE="com.example.notes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION="com.example.notes.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="com.example.notes.EXTRA_PRIORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        noteTitleEditText=findViewById(R.id.title_edittext);
        noteDescriptionEditText=findViewById(R.id.description_edittext);
        noteNumberPicker=findViewById(R.id.priority_numberpicker);
        noteNumberPicker.setMinValue(1);
        noteNumberPicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note_item:
                saveNote();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
    public void saveNote(){
        String noteTitle=noteTitleEditText.getText().toString();
        String noteDesc=noteDescriptionEditText.getText().toString();
        int notePriority=noteNumberPicker.getValue();
        Intent intent=new Intent();
        intent.putExtra(EXTRA_TITLE,noteTitle);
        intent.putExtra(EXTRA_DESCRIPTION,noteDesc);
        intent.putExtra(EXTRA_PRIORITY,notePriority);
        setResult(RESULT_OK,intent);
        finish();
    }
}
