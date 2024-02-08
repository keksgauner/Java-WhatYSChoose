package de.keksgauner.whatyschoose;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

import de.keksgauner.whatyschoose.utlis.WordHandler;

public class MainActivity extends AppCompatActivity {

    WordHandler wordHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.wordHandler = new WordHandler(this);

        // Set up list view
        ListView wordList = findViewById(R.id.word_list);
        wordList.setAdapter(this.wordHandler.getAdapter());

        // Long click to remove word
        wordList.setOnItemLongClickListener((adapterView, view, position, time) -> {
            this.wordHandler.removeWord(position);
            return true;
        });

        // Add word
        Button inputButtonAdd = findViewById(R.id.input_button_add);
        inputButtonAdd.setOnClickListener(view -> {
            EditText inputText = findViewById(R.id.input_text);
            String word = inputText.getText().toString().trim();

            if (word.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a word!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (this.wordHandler.containsWord(word)) {
                Toast.makeText(MainActivity.this, "Word already exists!", Toast.LENGTH_SHORT).show();
                return;
            }
            this.wordHandler.addWord(word);
            inputText.setText("");
        });

        // Random button
        Random random = new Random();
        Button randomButton = findViewById(R.id.random_button);
        randomButton.setOnClickListener(view -> {
            List<String> words = this.wordHandler.getWords();
            if (words.isEmpty()) {
                Toast.makeText(MainActivity.this, "No words to choose from!", Toast.LENGTH_SHORT).show();
                return;
            }

            Handler handler = new Handler();

            Runnable runnable = new Runnable() {
                int count = 0;
                int delay = 100;

                @Override
                public void run() {
                    int randomPosition = random.nextInt(words.size());
                    wordHandler.getAdapter().setSelectedPosition(randomPosition);

                    count++;

                    if (count < 10) {
                        delay += 100;
                        handler.postDelayed(this, delay);
                    } else {
                        String randomWord = words.get(randomPosition);
                        Toast.makeText(MainActivity.this, "Maybe choose: " + randomWord, Toast.LENGTH_SHORT).show();
                    }
                }
            };

            handler.post(runnable);
        });
    }
}