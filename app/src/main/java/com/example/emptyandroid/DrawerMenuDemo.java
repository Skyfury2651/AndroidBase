package com.example.emptyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class DrawerMenuDemo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    FrameLayout mainContent;
    Fragment01 fragment01;
    Fragment02 fragment02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu_demo);
        initView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav_view);
        toolbar = findViewById(R.id.drawer_toolbar);

        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                        R.string.open_menu_message,
                        R.string.close_menu_message);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        // on bottom menu click
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(this);

        // add fragment.
        mainContent = findViewById(R.id.main_content);
        fragment01 = new Fragment01();
        fragment02 = new Fragment02();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment01, Fragment01.class.getName())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_list_contact:
                Log.d(DrawerMenuDemo.class.getName(), "List contact");
                break;
            case R.id.menu_item_add_contact:
                Log.d(DrawerMenuDemo.class.getName(), "Add contact");
                break;
            case R.id.menu_item_list_song:
                Log.d(DrawerMenuDemo.class.getName(), "List song");
                break;
            case R.id.menu_item_create_song:
                Log.d(DrawerMenuDemo.class.getName(), "Create song");
                break;
            case R.id.menu_item_about:
                Log.d(DrawerMenuDemo.class.getName(), "About");
                break;
            case R.id.menu_item_policy:
                Log.d(DrawerMenuDemo.class.getName(), "Policy");
                break;
            case R.id.home_button:
                Log.d(DrawerMenuDemo.class.getName(), "Home");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment01, Fragment01.class.getName())
                        .commit();
                break;
            case R.id.song_button:
                Log.d(DrawerMenuDemo.class.getName(), "Song Bottom");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment02, Fragment02.class.getName())
                        .commit();
                break;
            case R.id.contact_button:
                Log.d(DrawerMenuDemo.class.getName(), "Contact");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return false;
    }
}