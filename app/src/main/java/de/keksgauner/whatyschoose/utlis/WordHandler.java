package de.keksgauner.whatyschoose.utlis;

import android.content.Context;

import java.util.LinkedList;
import java.util.List;

public class WordHandler {
    List<String> words;
    Adapter adapter;

    public WordHandler(Context context) {
        this.words = new LinkedList<>();
        this.adapter = new Adapter(context, android.R.layout.simple_list_item_1, words);
    }

    public List<String> getWords() {
        return this.words;
    }

    public Adapter getAdapter() {
        return this.adapter;
    }

    public void addWord(String word) {
        this.words.add(word);
        this.adapter.notifyDataSetChanged();
    }

    public void removeWord(int position) {
        this.words.remove(position);
        this.adapter.notifyDataSetChanged();
    }

    public boolean containsWord(String word) {
        return this.words.contains(word);
    }
}
