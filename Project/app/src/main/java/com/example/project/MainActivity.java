package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project.model.User;
import com.example.project.model.UserPreferences;
import com.example.project.view.ListGenresFragment;
import com.example.project.view.NoLoginFragment;
import com.example.project.view.PersonLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    private UserPreferences userPreferences;
    private TextView textViewUsername, textViewEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);



        drawerLayout = findViewById(R.id.drawerlayout);
       NavigationView navigationView = findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        textViewUsername = headerView.findViewById(R.id.textViewUsername);
        userPreferences = new UserPreferences(this);
        User user = userPreferences.getUser();
        if(user == null){
            textViewUsername.setText("Bạn chưa đăng nhập");
        }else{
            textViewUsername.setText(user.getUsername());
        }

       navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


        
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        userPreferences = new UserPreferences(this);
        User user = userPreferences.getUser();
       int id = item.getItemId();
        if(id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();

            getSupportActionBar().setTitle("Trang chủ");

        }else if(id == R.id.nav_account){

            if(user == null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NoLoginFragment()).commit();
                getSupportActionBar().setTitle("Tài khoản");

            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PersonLayout()).commit();
                getSupportActionBar().setTitle("Tài khoản");


            }

        } else if(id == R.id.logout){

            if(user == null){
                Toast.makeText(this, "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NoLoginFragment()).commit();
            }else {
                userPreferences.clearUser();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
                Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }
        }else if(id == R.id.nav_search){
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_genres) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListGenresFragment()).commit();

            getSupportActionBar().setTitle("Thể loại");

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // You can perform search here as the user types
                return true;
            }
        });
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}