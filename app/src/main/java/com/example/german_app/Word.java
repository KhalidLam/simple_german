package com.example.german_app;

public class Word {

    private String mDefaultTranslation;

    private String mGermanTranslation;

    private int mAudioResourceId;

    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /*public Word(String defaultTranslation, String germanTranslation) {
        mDefaultTranslation = defaultTranslation;
        mGermanTranslation = germanTranslation;
    }*/

    public Word(String defaultTranslation, String germanTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mGermanTranslation = germanTranslation;
        mAudioResourceId = audioResourceId;
    }

    public Word(String defaultTranslation, String germanTranslation, int imageResourceId, int audioResourceId) {
        mDefaultTranslation =   defaultTranslation;
        mGermanTranslation  =   germanTranslation;
        mImageResourceId    =   imageResourceId;
        mAudioResourceId    =   audioResourceId;
    }

    /**
     * Returns the word in english (default)
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Returns the german translation of the word
     */
    public String getGermanTranslation() {
        return mGermanTranslation;
    }

    /**
     * Returns the image resource ID of the word
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Returns the image resource ID of the word
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

}
