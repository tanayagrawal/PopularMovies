package com.tanayagrawal.popularmoviesstageone.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.tanayagrawal.popularmoviesstageone.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tanayagrawal on 27/03/16.
 */
class ImageViewHolder {
    @Bind(R.id.movie_image)
    ImageView imageView;

    public ImageViewHolder(View view){
        ButterKnife.bind(this, view);
    }
}