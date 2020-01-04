package com.royalhouse.coffiegarden.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.royalhouse.coffiegarden.Adapter.AdapterClient;
import com.royalhouse.coffiegarden.Adapter.AdapterProdukTerbaru;
import com.royalhouse.coffiegarden.Adapter.SlidingImageAdapter;
import com.royalhouse.coffiegarden.Constant.SuperDialog;
import com.royalhouse.coffiegarden.R;
import com.royalhouse.coffiegarden.Services.ApiClient;
import com.royalhouse.coffiegarden.Services.RetrofitService;
import com.royalhouse.coffiegarden.model.modelClient;
import com.royalhouse.coffiegarden.model.modelProduk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView rV_PTerlaris, rV_PTerbaru, rV_Client;
    private ArrayList<String> listBanner = new ArrayList<>();
    private SwipeRefreshLayout frameLayout;

    private ArrayList<modelClient> listClient= new ArrayList<>();
    private ArrayList<modelProduk> listProduk = new ArrayList<>();
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;
    final int NUM_PAGES = 3;

    ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        frameLayout = view.findViewById(R.id.refreshHome);
        frameLayout.setRefreshing(true);
        frameLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataCommentClient();
                getDataProduk();
            }
        });

        rV_PTerbaru = view.findViewById(R.id.recyclerView);
        rV_PTerbaru.hasFixedSize();
        rV_PTerbaru.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        rV_PTerlaris = view.findViewById(R.id.recyclerView2);
        rV_PTerlaris.hasFixedSize();
        rV_PTerlaris.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        rV_Client = view.findViewById(R.id.rV_Client);
        rV_Client.hasFixedSize();
        rV_Client.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        getDataCommentClient();
        getDataProduk();

         viewPager = view.findViewById(R.id.viewPager);

        getDataBanner();
        return view;
    }

    private void getDataBanner() {
        frameLayout.setRefreshing(true);
        final ApiClient apiClient = new RetrofitService().getPublicService().create(ApiClient.class);
        final Call<String> call = apiClient.dataBanner();
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
                                listBanner.add(
                                        objDataCuti.getString("imageBanner"));
                            } catch (Exception e) {
                                Log.i("asd", e.toString());
                            }
                        }
                        SlidingImageAdapter adapter = new SlidingImageAdapter(listBanner,getResources().getStringArray(R.array.slideBanner), getContext());
                        viewPager.setAdapter(adapter);
                        /*After setting the adapter use the timer */
                        final Handler handler = new Handler();
                        final Runnable Update = new Runnable() {
                            public void run() {
                                if (currentPage == NUM_PAGES-1) {
                                    currentPage = 0;
                                }
                                viewPager.setCurrentItem(currentPage++, true);
                            }
                        };

                        timer = new Timer(); // This will create a new Thread
                        timer.schedule(new TimerTask() { // task to be scheduled
                            @Override
                            public void run() {
                                handler.post(Update);
                            }
                        }, DELAY_MS, PERIOD_MS);

                    } else {
                        Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                frameLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                frameLayout.setRefreshing(false);
            }
        });
    }

    private void getDataProduk() {
        frameLayout.setRefreshing(true);

        final ApiClient apiClient = new RetrofitService().getPublicService().create(ApiClient.class);
        final Call<String> call = apiClient.dataProduk();
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
                        AdapterProdukTerbaru client = new AdapterProdukTerbaru(listProduk, getContext());
                        rV_PTerbaru.setAdapter(client);
                        rV_PTerlaris.setAdapter(client);

                    } else {
                        Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                frameLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                frameLayout.setRefreshing(false);
            }
        });
    }



    private void getDataCommentClient() {
        frameLayout.setRefreshing(true);

        final ApiClient apiClient = new RetrofitService().getPublicService().create(ApiClient.class);
        final Call<String> call = apiClient.dataClient();
        listClient.clear();
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
                                listClient.add(new modelClient(
                                        objDataCuti.getString("imageClient"),
                                        objDataCuti.getString("nameClient"),
                                        objDataCuti.getString("profClient"),
                                        objDataCuti.getString("commentClient")));
                            } catch (Exception e) {
                                Log.i("asd", e.toString());
                            }
                        }
                        AdapterClient client = new AdapterClient(listClient, getContext());
                        rV_Client.setAdapter(client);


                    } else {
                        Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                frameLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(),"Error get Data in Server", Toast.LENGTH_SHORT).show();
                frameLayout.setRefreshing(false);
            }
        });
    }
}
