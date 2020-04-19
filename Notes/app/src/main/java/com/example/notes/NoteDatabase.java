package com.example.notes;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version =1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDao noteDao();
    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseWriteExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                NoteDao dao = instance.noteDao();
                dao.deleteAllNotes();

               Note note=new Note("Learn architecture competitions  ","android architecture components are a collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.\n" +
                       "\n" +
                       "Learn the basics of putting together a robust app with the Guide to app architecture.\n" +
                       "Manage your app's lifecycle. New lifecycle-aware components help you manage your activity and fragment lifecycles. Survive configuration changes, avoid memory leaks and easily load data into your UI.\n" +
                       "Use LiveData to build data objects that notify views when the underlying database changes.\n" +
                       "ViewModel stores UI-related data that isn't destroyed on app rotations.\n" +
                       "Room is a SQLite object mapping library. Use it to avoid boilerplate code and easily convert SQLite table data to Java objects. Room provides compile time checks of SQLite statements and can return RxJava, Flowable and LiveData observables.",1);
               dao.insert(note);
            });
        }

    };
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


}
