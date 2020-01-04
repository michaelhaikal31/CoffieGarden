package com.royalhouse.coffiegarden.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.royalhouse.coffiegarden.R;
import com.squareup.picasso.Picasso;

public class DetailProdukActivity extends AppCompatActivity {
    private TextView NameProduk, hargaProduk, RatingProduk, DeskripsiProduk;
    private ImageView ImageProduk;
    private Context context;
    private RatingBar ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        NameProduk = findViewById(R.id.NameProduk);
        ImageProduk = findViewById(R.id.imageView);
        hargaProduk = findViewById(R.id.hrgProduk);
        RatingProduk = findViewById(R.id.RatingProduk);
        DeskripsiProduk= findViewById(R.id.DeskripsiProduk);
        ratingbar = findViewById(R.id.ratingbar);

        Bundle bundle = getIntent().getExtras();
        NameProduk.setText(bundle.getString("nameProduk"));
        Picasso.with(context).load(bundle.getString("imageProduk")).into(ImageProduk);
        hargaProduk.setText(bundle.getString("hargaProduk"));
//        RatingProduk.setText(bundle.getInt("start"));
        DeskripsiProduk.setText(bundle.getString("deskripsiProduk"));
//        ratingbar.setRating(Float.parseFloat(String.valueOf(bundle.getInt("start"))));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
