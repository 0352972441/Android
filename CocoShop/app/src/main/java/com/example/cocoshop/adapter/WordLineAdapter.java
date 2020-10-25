package com.example.cocoshop.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.R;

import java.util.ArrayList;
import java.util.List;

public class WordLineAdapter extends RecyclerView.Adapter<WordLineAdapter.ViewHolder> {
    private String wordList;
    private List<TextView> textViewList;

    public WordLineAdapter(String wordList) {
        this.wordList = wordList;
        textViewList = new ArrayList<>();
    }

    @NonNull
    @Override
    public WordLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WordLineAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.word_line_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final WordLineAdapter.ViewHolder holder, int position) {
        if (wordList != null) {
            holder.txWordLine.setText("");
            holder.txWordLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.txWordLine.setText("");
                }
            });
            textViewList.add(holder.txWordLine);
        }
    }

    public TextView getViewPosition(int position){
        return textViewList.get(position);
    }

    public String getAnswer(){
        StringBuilder answer = new StringBuilder();
        for(TextView i : textViewList){
            answer.append(i.getText().toString());
        }
        return answer.toString().trim();
    }

    @Override
    public int getItemCount() {
        return wordList.length();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txWordLine;

        public ViewHolder(@NonNull View view) {
            super(view);
            txWordLine = view.findViewById(R.id.tx_word_line);
        }
    }
}
