package com.tuyp.newresto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.tuyp.newresto.View.Fragment.SashimiFragment;
import com.tuyp.newresto.View.Fragment.SushiFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView titleText;
    String title;
    ActionBarDrawerToggle toggle;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button btnSashimi,btnSushi,btnMainCourse,btnBeverage;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        titleText = findViewById(R.id.textTitle);
        btnSashimi = findViewById(R.id.sashimibtn);
        btnSushi = findViewById(R.id.sushirollbtn);
        btnMainCourse = findViewById(R.id.maincoursebtn);
        btnBeverage = findViewById(R.id.beveragebtn);
        sharedPreferences = getSharedPreferences("order",MODE_PRIVATE);
        Log.d("data1","masuk oncreate");
        if (sharedPreferences.getString("data-order",null) != null){
            Log.d("dataaaaaaaaa","masuk tidak null");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().commit();
        }else {
            Log.d("dataaaaaa","masuk null");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().commit();
        }

        btnSushi.setOnClickListener(this);
        btnSashimi.setOnClickListener(this);
        btnMainCourse.setOnClickListener(this);
        btnBeverage.setOnClickListener(this);
        title = "Choose the food you love";

        SpannableStringBuilder ssb = new SpannableStringBuilder(title);

        ForegroundColorSpan markColor = new ForegroundColorSpan(getResources().getColor(R.color.colorTint));
        ssb.setSpan(markColor,11,15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleText.setText(ssb);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_sort_black_24dp);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SashimiFragment()).commit();
        }

        fragmentManager = getSupportFragmentManager();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sashimibtn:
//                sharedPreferences.getString("data-order",null);
                sharedPreferences.edit().clear().commit();
                btnSashimi.setBackground(getResources().getDrawable(R.drawable.button_clicked));
                btnSashimi.setTextColor(getResources().getColor(R.color.backgroundPrimary));
                btnSushi.setBackground(getResources().getDrawable(R.drawable.custom_button));
                btnSushi.setTextColor(getResources().getColor(R.color.colorTint));
                btnMainCourse.setBackground(getResources().getDrawable(R.drawable.custom_button));
                btnMainCourse.setTextColor(getResources().getColor(R.color.colorTint));
                btnBeverage.setBackground(getResources().getDrawable(R.drawable.custom_button));
                btnBeverage.setTextColor(getResources().getColor(R.color.colorTint));
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new SashimiFragment()).commit();
                break;
            case R.id.sushirollbtn:
                fragmentTransaction = fragmentManager.beginTransaction();
                btnSushi.setBackground(getResources().getDrawable(R.drawable.button_clicked));
                btnSushi.setTextColor(getResources().getColor(R.color.backgroundPrimary));
                btnSashimi.setBackground(getResources().getDrawable(R.drawable.custom_button));
                btnSashimi.setTextColor(getResources().getColor(R.color.colorTint));
                btnMainCourse.setBackground(getResources().getDrawable(R.drawable.custom_button));
                btnMainCourse.setTextColor(getResources().getColor(R.color.colorTint));
                btnBeverage.setBackground(getResources().getDrawable(R.drawable.custom_button));
                btnBeverage.setTextColor(getResources().getColor(R.color.colorTint));
                fragmentTransaction.replace(R.id.fragment_container, new SushiFragment()).commit();
                break;
//            case R.id.maincoursebtn:
//                fragmentTransaction = fragmentManager.beginTransaction();
//                btnSushi.setBackground(getResources().getDrawable(R.drawable.custom_button));
//                btnSushi.setTextColor(getResources().getColor(R.color.colorTint));
//                btnSashimi.setBackground(getResources().getDrawable(R.drawable.custom_button));
//                btnSashimi.setTextColor(getResources().getColor(R.color.colorTint));
//                btnMainCourse.setBackground(getResources().getDrawable(R.drawable.button_clicked));
//                btnMainCourse.setTextColor(getResources().getColor(R.color.backgroundPrimary));
//                btnBeverage.setBackground(getResources().getDrawable(R.drawable.custom_button));
//                btnBeverage.setTextColor(getResources().getColor(R.color.colorTint));
//                fragmentTransaction.replace(R.id.fragment_container,new MainCourseFragment()).commit();
//                break;
//            case R.id.beveragebtn:
//                fragmentTransaction = fragmentManager.beginTransaction();
//                btnSushi.setBackground(getResources().getDrawable(R.drawable.custom_button));
//                btnSushi.setTextColor(getResources().getColor(R.color.colorTint));
//                btnSashimi.setBackground(getResources().getDrawable(R.drawable.custom_button));
//                btnSashimi.setTextColor(getResources().getColor(R.color.colorTint));
//                btnMainCourse.setBackground(getResources().getDrawable(R.drawable.custom_button));
//                btnMainCourse.setTextColor(getResources().getColor(R.color.colorTint));
//                btnBeverage.setBackground(getResources().getDrawable(R.drawable.button_clicked));
//                btnBeverage.setTextColor(getResources().getColor(R.color.backgroundPrimary));
//                fragmentTransaction.replace(R.id.fragment_container,new BeverageFragment()).commit();
//                break;

            default:

                break;
        }
    }
}
