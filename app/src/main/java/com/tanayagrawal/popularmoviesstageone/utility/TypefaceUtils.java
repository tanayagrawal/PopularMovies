package com.tanayagrawal.popularmoviesstageone.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by tanayagrawal on 27/03/16.
 */
public class TypefaceUtils {

    Context context;

    private final String TAG = TypefaceUtils.class.getSimpleName();
    private final String regularPath = "fonts/RobotoCondensed-Regular.ttf";

    public TypefaceUtils(Context context) {
        this.context = context;
    }


    public void setButtonTypeface(Button button) {
        try {

            Typeface tf = getFont(context);
            if (tf != null) {
                button.setTypeface(tf);
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    public void setTextViewTypeface(TextView textView) {
        try {
            Typeface tf = getFont(context);
            if (tf != null) {
                textView.setTypeface(tf);
            }
        } catch (Exception e) {
        }
    }

    public void setRadioButtonTypeface(RadioButton radioButton){
        try {
            Typeface tf = getFont(context);
            if (tf != null) {
                radioButton.setTypeface(tf);
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    public Typeface getFont(Context context) {

        Typeface tf = Typeface.createFromAsset(context.getAssets(), regularPath);
        return tf;
    }

}
