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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterClient extends RecyclerView.Adapter<AdapterClient.ViewHolder> {
    private ArrayList<modelClient> DataList;
    private Context context;
    private setOnclickDataEdit onClick;
    Activity activity;
    public AdapterClient(ArrayList<modelClient> list, Context context ){
        this.DataList = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_data_client, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.commentClinet.setText(DataList.get(i).getCommentClient());
        viewHolder.nameClient.setText(DataList.get(i).getNamaClient());
        viewHolder.profClient.setText(DataList.get(i).getProvisiClient());
        Picasso.with(context).load(DataList.get(i).getImgClient()).into(viewHolder.imageClient);
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageClient;
        private TextView nameClient, profClient, commentClinet;
        public ViewHolder(View view) {
            super(view);
            imageClient = view.findViewById(R.id.imageClient);
            nameClient = view.findViewById(R.id.nameClient);
            profClient = view.findViewById(R.id.profClient);
            commentClinet = view.findViewById(R.id.commentClinet);
        }
    }
    public interface setOnclickDataEdit{
        public abstract  void onclick(int posisi);

    }
}
