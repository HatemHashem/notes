package com.example.notes;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {
    NoteRepository noteRepository;
    LiveData<List<Note>>allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);
        allNotes=noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
    public void insert(Note note){
        noteRepository.insert(note);
    }
    public void update(Note note){
        noteRepository.update(note);
    }
    public void delete(Note note){
        noteRepository.delete(note);
    }
    public void deleteAll(){
        noteRepository.deleteAll();
    }
}
