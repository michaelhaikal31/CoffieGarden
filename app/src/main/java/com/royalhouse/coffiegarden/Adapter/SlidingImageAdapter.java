package com.royalhouse.coffiegarden.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.royalhouse.coffiegarden.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SlidingImageAdapter extends PagerAdapter {
    private ArrayList<String> imageArray;
    private String[] nameArray;
    private Context context;
    private LayoutInflater inflater;

    public SlidingImageAdapter(ArrayList<String>  imageArray, String[] nameArray, Context context) {
        this.imageArray = imageArray;
        this.nameArray = nameArray;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.slidingimages_layout, container,false);
        ImageView imageView = view.findViewById(R.id.imageView4);
        Picasso.with(context).load(imageArray.get(position)).into(imageView);
//        imageView.setImageResource(imageArray[position]);
        TextView textView = view.findViewById(R.id.textView7);
        textView.setText(nameArray[position]);
        ((ViewPager)container).addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageArray.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((View)o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}
