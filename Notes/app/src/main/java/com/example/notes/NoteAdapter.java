package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> notes;


    public NoteAdapter() {
        notes=new ArrayList<Note>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote=notes.get(position);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView priorityTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView=itemView.findViewById(R.id.title_textview);
            descriptionTextView=itemView.findViewById(R.id.description_textview);
            priorityTextView=itemView.findViewById(R.id.priority_textview);
        }
        public void bindTo(Note currentNote){
            titleTextView.setText(currentNote.getTitle());
            descriptionTextView.setText(currentNote.getDescription());
            priorityTextView.setText(String.valueOf(currentNote.getPriority()));
        }
    }
}
