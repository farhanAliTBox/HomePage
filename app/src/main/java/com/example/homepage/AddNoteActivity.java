package com.example.homepage;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText noteTitleInput, noteDescriptionInput, noteDateInput;
    private Button saveNoteButton, deleteNoteButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_activtiy);

        databaseHelper = new DatabaseHelper(this);

        noteTitleInput = findViewById(R.id.note_title_input);
        noteDescriptionInput = findViewById(R.id.note_description_input);
        noteDateInput = findViewById(R.id.note_date_input);
        saveNoteButton = findViewById(R.id.save_note_button);
        deleteNoteButton = findViewById(R.id.delete_note_button);

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });
    }

    private void saveNote() {
        String title = noteTitleInput.getText().toString().trim();
        String description = noteDescriptionInput.getText().toString().trim();
        String date = noteDateInput.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(date)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note = new Note(title, description, date);
        long id = databaseHelper.addNote(note);

        if (id > 0) {
            Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
            finish(); // Close activity and return to MainActivity
        } else {
            Toast.makeText(this, "Failed to add note", Toast.LENGTH_SHORT).show();
        }
    }
}
