package com.royalhouse.coffiegarden.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.royalhouse.coffiegarden.Activity.DetailProdukActivity;
import com.royalhouse.coffiegarden.Adapter.AdapterProduk;
import com.royalhouse.coffiegarden.Adapter.AdapterProdukTerbaru;
import com.royalhouse.coffiegarden.R;
import com.royalhouse.coffiegarden.Services.ApiClient;
import com.royalhouse.coffiegarden.Services.RetrofitService;
import com.royalhouse.coffiegarden.model.modelProduk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdukFragment extends Fragment implements AdapterProduk.setOnclickDataEdit{
    private RecyclerView rvPageProduk;
    private SwipeRefreshLayout refreshProduk;
    private ArrayList<modelProduk> listProduk = new ArrayList<>();
    public ProdukFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_produk, container, false);
        refreshProduk = view.findViewById(R.id.refreshProduk);
        refreshProduk.setRefreshing(true);
        refreshProduk.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataProduk();
            }
        });

        rvPageProduk = view.findViewById(R.id.rvPageProduk);
        rvPageProduk.hasFixedSize();
        rvPageProduk.setLayoutManager(new GridLayoutManager(getContext(), 2));
        getDataProduk();

        return  view;
    }

    private void getDataProduk() {
        refreshProduk.setRefreshing(true);

        final ApiClient apiClient = new RetrofitService().getPublicService().create(ApiClient.class);
        final Call<String> call = apiClient.dataProdukList();
        listProduk.clear();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("asd responseServer " + response.body());
                try {
                    JSONObject object = new JSONObject(response.body());
                    final String getRcResul = object.getString("rc");
                    if (getRcResul.equalsIgnoreCase("sukses")) {
                        JSONArray arrayData = object.getJSONArray("data");
                        JSONObject objDataCuti;
                        for (int i = 0; i < arrayData.length(); i++) {
                            try {
                                objDataCuti = arrayData.getJSONObject(i);
                                listProduk.add(new modelProduk(
                                        objDataCuti.getInt("start"),
                                        objDataCuti.getString("nameProduk"),
                                        objDataCuti.getString("hargaProduk"),
                                        objDataCuti.getString("imageProduk"),
                                        objDataCuti.getString("deskripsiProduk")));
                            } catch (Exception e) {
                                Log.i("asd", e.toString());
                            }
                        }
                        AdapterProduk client = new AdapterProduk(listProduk, getContext(), ProdukFragment.this);
                        rvPageProduk.setAdapter(client);

                    } else {
                        Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                refreshProduk.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                refreshProduk.setRefreshing(false);
            }
        });
    }

    @Override
    public void onclick(int posisi) {
        final Intent intent = new Intent(getActivity(), DetailProdukActivity.class);
        intent.putExtra("nameProduk", listProduk.get(posisi).getNameProduk());
        intent.putExtra("hargaProduk", listProduk.get(posisi).getHargaProduk());
        intent.putExtra("imageProduk", listProduk.get(posisi).getImageProduk());
        intent.putExtra("deskripsiProduk", listProduk.get(posisi).getDeskripsiProduk());
        intent.putExtra("start", listProduk.get(posisi).getStart());
        startActivity(intent);
    }
}
