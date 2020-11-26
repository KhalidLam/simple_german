package com.example.german_app;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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

        words.add(new Word("Father", "Der Vater", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("Mother", "Die Mutter", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("Son", "Der Sohn", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("Daughter", "Die Tochter", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("Brother", "Der Bruder", R.drawable.family_older_brother, R.raw.family_younger_brother));
        words.add(new Word("Sister", "Die Schwester", R.drawable.family_older_sister, R.raw.family_younger_sister));
        words.add(new Word("Oma", "Großmutter", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("Grandfather", "Opa", R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

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
            mMediaPlayer = MediaPlayer.create(FamilyActivity.this, clickedWord.getAudioResourceId());

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
