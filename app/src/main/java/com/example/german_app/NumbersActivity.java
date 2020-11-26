package com.example.german_app;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    // Handle playback of all audio files
    private MediaPlayer mMediaPlayer;

    // Handle audio focus when playing a sound file
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        // Resume playback
                        mMediaPlayer.start();
                    }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        // Stop playback and clean up resources
                        releaseMediaPlayer();
                    }
                }
            };

    /**
     *  This listener gets triggered when the MediaPlayer has completed
     *  Playing the audio file
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //String[] words = {"zero","one","two","three","four","five","six","seven","eight","nine","ten"};

        // Create and setup the AudioManager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create an ArrayList of words
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("One", "eins", R.drawable.number_one, R.raw.number_one ));
        words.add(new Word("Two", "zwei", R.drawable.number_two, R.raw.number_two ));
        words.add(new Word("Three", "drei", R.drawable.number_three, R.raw.number_three ));
        words.add(new Word("Four", "vier", R.drawable.number_four, R.raw.number_four ));
        words.add(new Word("Five", "fünf", R.drawable.number_five, R.raw.number_five ));
        words.add(new Word("Six", "sechs", R.drawable.number_six, R.raw.number_six ));
        words.add(new Word("Seven", "sieben", R.drawable.number_seven, R.raw.number_seven ));
        words.add(new Word("Eight", "acht", R.drawable.number_eight, R.raw.number_eight ));
        words.add(new Word("Nine", "neun", R.drawable.number_nine, R.raw.number_nine ));
        //words.add(new Word("Ten", "zehn", R.drawable.number_ten, R.raw.number_zehn ));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            // Get the Word object at the given position the user clicked on
            Word clickedWord = words.get(position);

            // Release the media player if it currently exists bcs we are about to
            // play a different sound file.
            releaseMediaPlayer();

            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                    // Use the music stream.
                    AudioManager.STREAM_MUSIC,
                    // Request permanent focus.
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                // We have audio focus

                // Create and setup the MediaPlayer for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, clickedWord.getAudioResourceId());

                // Start the audio file
                mMediaPlayer.start();

                // Setup a listener on the media player , so that we can stop and release the
                // media player  once the sounds has finished playing
                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if(mMediaPlayer != null){
            // Release media player resources
            mMediaPlayer.release();

            // Set the media player back to null.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }



}
