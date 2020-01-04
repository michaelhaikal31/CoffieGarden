package com.royalhouse.coffiegarden.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.royalhouse.coffiegarden.R;
import com.royalhouse.coffiegarden.fragment.HomeFragment;
import com.royalhouse.coffiegarden.fragment.LoginFragment;
import com.royalhouse.coffiegarden.fragment.ProdukFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.menu_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (menuItem.getItemId()) {
            case R.id.home_menu:
                fragment = new HomeFragment();
                title = "Home";
                break;

            case R.id.profile_menu:
                fragment = new LoginFragment();
                title = "About me";
                break;

            case R.id.produk_menu:
                fragment = new ProdukFragment();
                title = "Produk";
                break;
        }


        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFrameLayout, fragment)
                    .commit();
            getSupportActionBar().setTitle(title);
            return true;
        } else {
            return false;
        }
    }
}
