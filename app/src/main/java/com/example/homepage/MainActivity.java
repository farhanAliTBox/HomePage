package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        loadNotes();
    }

    private void loadNotes() {
        List<Note> notes = databaseHelper.getAllNotes();
        noteAdapter = new NoteAdapter(notes, new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
                intent.putExtra("NOTE_ID", note.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes(); // Refresh notes when returning to this activity
    }
}
