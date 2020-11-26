package com.example.german_app;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new Word("What is your name?", "Wie heißen Sie?", R.raw.phrase_whatisyourname));
        words.add(new Word("Good luck", "Viel Glück", R.raw.phrase_goodluck));
        words.add(new Word("Repeat that please", "Wiederholen sie das bitte", R.raw.phrase_repeatplease));
        words.add(new Word("Congratulations", "Herzlichen glückwunsch", R.raw.phrase_congratulations));
        words.add(new Word("Do you understand me?", "Verstehen Sie mich?", R.raw.phrase_doyouunderstand));
        words.add(new Word("Do you have a question?", "Haben sie ein frage?", R.raw.phrase_doyouhaveaquestion));
        words.add(new Word("Where are you from ?", "Wo kommen sie her?", R.raw.phrase_whereareyoufrom));
        words.add(new Word("Happy Birthday!", "Herzlichen Glückwunsch zum Geburtstag", R.raw.phrase_happybirthday));
        words.add(new Word("Please!", "Bitte!", R.raw.phrase_please));
        words.add(new Word("I am glad to meet you", "Freut mich Sie kennenzulernen", R.raw.phrase_pleasedtomeetyou));
        words.add(new Word("Thank you", "Vielen dank", R.raw.phrase_thankyou));
        words.add(new Word("I don't understand", "Ich verstehe nicht", R.raw.phrase_idontunderstand));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

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
            mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, clickedWord.getAudioResourceId());

            // Start the audio file
            mMediaPlayer.start();

            // Setup a listener on the media player , so that we can stop and release the
            // media player  once the sounds has finished playing
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();

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
