package com.example.german_app;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    // Handle playback of all audio files
    MediaPlayer mMediaPlayer;

    /**
     *  This listener gets triggered when the MediaPlayer has completed
     *  Playing the audio file
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an ArrayList of words
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Black", "Schwarz", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("White", "Weiß", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("Red", "Rot", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("Green", "Grün", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("Brown", "Braun", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("Gray", "Grau", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("Yellow", "Gelb", R.drawable.color_dusty_yellow, R.raw.color_yellow));
        //words.add(new Word("Orange", "Orange", R.drawable.color_orange));
        //words.add(new Word("Blue", "Blau", R.drawable.color_blue));
        //words.add(new Word("Pink", "Rosa", R.drawable.color_orange));
        //words.add(new Word("Gold", "Gold", R.drawable.color_gold));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, l) -> {

            // Get the Word object at the given position the user clicked on
            Word clickedWord = words.get(position);

            // Release the media player if it currently exists bcs we are about to
            // play a different sound file.
            releaseMediaPlayer();

            // Create and setup the MediaPlayer for the audio resource associated
            // with the current word
            mMediaPlayer = MediaPlayer.create(ColorsActivity.this, clickedWord.getAudioResourceId());

            // Start the audio file
            mMediaPlayer.start();

            // Setup a listener on the media player , so that we can stop and release the
            // media player  once the sounds has finished playing
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        });

    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
