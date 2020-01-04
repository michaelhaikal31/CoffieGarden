package com.royalhouse.coffiegarden.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.royalhouse.coffiegarden.R;
import com.royalhouse.coffiegarden.model.modelProduk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.ViewHolder> {
    private ArrayList<modelProduk> DataList;
    private Context context;
    private AdapterProduk.setOnclickDataEdit onClick;
    Activity activity;
    public AdapterProduk(ArrayList<modelProduk> list, Context context, AdapterProduk.setOnclickDataEdit onClick ){
        this.DataList = list;
        this.context = context;
        this.onClick = onClick;
    }
    @NonNull
    @Override
    public AdapterProduk.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_data_produklist, viewGroup, false);
        AdapterProduk.ViewHolder viewHolder = new AdapterProduk.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduk.ViewHolder viewHolder, final int i) {

        viewHolder.nameProduk.setText(DataList.get(i).getNameProduk());
        viewHolder.hargaProduk.setText(DataList.get(i).getHargaProduk());
        Picasso.with(context).load(DataList.get(i).getImageProduk()).into(viewHolder.imageProduk);
        viewHolder.cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null){
                    onClick.onclick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageProduk;
        private TextView nameProduk, hargaProduk, ratingBar2;
        private CardView cardView4;
        public ViewHolder(View view) {
            super(view);
            imageProduk = view.findViewById(R.id.imageProduk);
            nameProduk = view.findViewById(R.id.nameProduk);
            hargaProduk = view.findViewById(R.id.hargaProduk);
            cardView4 = view.findViewById(R.id.cardView4);
        }
    }
    public interface setOnclickDataEdit{
        public abstract  void onclick(int posisi);

    }
}
