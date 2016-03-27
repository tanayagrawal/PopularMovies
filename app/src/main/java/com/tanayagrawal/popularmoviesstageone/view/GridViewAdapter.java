package com.tanayagrawal.popularmoviesstageone.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;
import com.tanayagrawal.popularmoviesstageone.R;

import java.util.ArrayList;

/**
 * Created by tanayagrawal on 27/03/16.
 */
public class GridViewAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<String> mImagePaths = new ArrayList<String>();
    ImageViewHolder viewHolder;

    public GridViewAdapter(Context context, ArrayList<String> imagePaths) {
        mContext = context;
        mImagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return mImagePaths.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            viewHolder = (ImageViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.grid_view_item, parent, false);
            viewHolder = new ImageViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        Picasso.with(mContext).load(mImagePaths.get(position))
                .centerInside()
                .resize(500, 500)
                .placeholder(R.drawable.placeholder_image)
                .into(viewHolder.imageView);

        return convertView;
    }


}
