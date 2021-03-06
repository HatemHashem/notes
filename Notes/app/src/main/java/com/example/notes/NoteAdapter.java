package com.example.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.notes.MainActivity.EDIT_NOTE_REQUEST;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> notes;
    private Context context;


    public NoteAdapter(Context context) {
        notes = new ArrayList<Note>();
        this.context = context;
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.bindTo(currentNote);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView priorityTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_textview);
            descriptionTextView = itemView.findViewById(R.id.description_textview);
            priorityTextView = itemView.findViewById(R.id.priority_textview);
            itemView.setOnClickListener(this);
        }

        public void bindTo(Note currentNote) {
            titleTextView.setText(currentNote.getTitle());
            descriptionTextView.setText(currentNote.getDescription());
            priorityTextView.setText(String.valueOf(currentNote.getPriority()));
        }

        @Override
        public void onClick(View view) {
            Note currentNote = notes.get(getAdapterPosition());

            Intent intent = new Intent(context, AddNoteActivity.class);
            intent.putExtra(AddNoteActivity.EXTRA_TITLE, currentNote.getTitle());
            intent.putExtra(AddNoteActivity.EXTRA_DESCRIPTION, currentNote.getDescription());
            intent.putExtra(AddNoteActivity.EXTRA_PRIORITY, currentNote.getPriority());
            intent.putExtra(AddNoteActivity.EXTRA_ID, currentNote.getId());
            ((Activity) context).startActivityForResult(intent, EDIT_NOTE_REQUEST);

        }
    }
}
