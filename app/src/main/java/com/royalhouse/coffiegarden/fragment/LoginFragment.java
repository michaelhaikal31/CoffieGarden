package com.royalhouse.coffiegarden.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.royalhouse.coffiegarden.R;
import com.royalhouse.coffiegarden.Services.ApiClient;
import com.royalhouse.coffiegarden.Services.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private Button btnLogin;
    private TextView txtSignUp;
    private JSONObject paramObject;
    private EditText txtUsername, txtPassword;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        txtSignUp = view.findViewById(R.id.txtSignUp);
        txtUsername = view.findViewById(R.id.txtUsername);
        txtPassword = view.findViewById(R.id.txtPassword);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFrameLayout, new SignUpFragment())
                        .commit();
            }
        });

        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
            }
        });



        return view;
    }
    void setLogin(){
        final ApiClient apiClient = new RetrofitService().getInstance().create(ApiClient.class);
        paramObject = new JSONObject();
        try {
            paramObject.put("username", txtUsername.getText().toString());
            paramObject.put("password", txtPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Call<String> call = apiClient.mlogin(paramObject.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("asd reponse "+ response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("asd Error");
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}
