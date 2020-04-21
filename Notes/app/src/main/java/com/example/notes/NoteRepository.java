package com.example.notes;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase db = NoteDatabase.getInstance(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insert(note);
        });

    }

    public void update(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.update(note);
        });
    }

    public void delete(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.delete(note);
        });
    }

    public void deleteAll() {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.deleteAllNotes();
        });
    }
}
