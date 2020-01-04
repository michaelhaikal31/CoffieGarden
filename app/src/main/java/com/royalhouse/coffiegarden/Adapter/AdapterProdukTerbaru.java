package com.royalhouse.coffiegarden.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.royalhouse.coffiegarden.R;
import com.royalhouse.coffiegarden.model.modelClient;
import com.royalhouse.coffiegarden.model.modelProduk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProdukTerbaru extends RecyclerView.Adapter<AdapterProdukTerbaru.ViewHolder> {
    private ArrayList<modelProduk> DataList;
    private Context context;
    private AdapterClient.setOnclickDataEdit onClick;
    Activity activity;
    public AdapterProdukTerbaru(ArrayList<modelProduk> list, Context context ){
        this.DataList = list;
        this.context = context;
    }
    @NonNull
    @Override
    public AdapterProdukTerbaru.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_data_produk, viewGroup, false);
        AdapterProdukTerbaru.ViewHolder viewHolder = new AdapterProdukTerbaru.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProdukTerbaru.ViewHolder viewHolder, final int i) {

        viewHolder.nameProduk.setText(DataList.get(i).getNameProduk());
        viewHolder.hargaProduk.setText(DataList.get(i).getHargaProduk());
        Picasso.with(context).load(DataList.get(i).getImageProduk()).into(viewHolder.imageProduk);
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageProduk;
        private TextView nameProduk, hargaProduk, ratingBar2;
        public ViewHolder(View view) {
            super(view);
            imageProduk = view.findViewById(R.id.imageProduk);
            nameProduk = view.findViewById(R.id.nameProduk);
            hargaProduk = view.findViewById(R.id.hargaProduk);

        }
    }
    public interface setOnclickDataEdit{
        public abstract  void onclick(int posisi);

    }
}
