package com.course.msp.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.course.msp.R;
import com.course.msp.databinding.ActivityMainBinding;
import com.course.msp.domain.dto.FoodInfor;

public class InforActivity extends AppCompatActivity {

    private FoodInfor foodInfor = new FoodInfor();
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_information_meal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d("INFO" , "왜 안될까 게씨빌");
        initIntentInfor(getIntent());

        ImageView image = (ImageView) findViewById(R.id.showImage);
        image.setImageURI(foodInfor.getImage());

        TextView nameView = (TextView) findViewById(R.id.inforName);
        nameView.setText(foodInfor.getFoodName());

        TextView countView = (TextView) findViewById(R.id.inforCount);
        countView.setText(foodInfor.getFoodCount());

        TextView feelView = (TextView) findViewById(R.id.inforFeel);
        feelView.setText(foodInfor.getFoodFeel());

        TextView dateView = (TextView) findViewById(R.id.inforDate);
        dateView.setText(foodInfor.getTime());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initIntentInfor(Intent intent){
        Log.d("Info", intent.getStringExtra("nameInfor"));
        Log.d("Info", intent.getStringExtra("countInfor"));
        Log.d("Info", intent.getStringExtra("feelInfor"));
        Log.d("Info", intent.getStringExtra("dateInfor"));
        this.foodInfor.setFoodName(intent.getStringExtra("nameInfor"));
        this.foodInfor.setFoodCount(intent.getStringExtra("countInfor"));
        this.foodInfor.setFoodFeel(intent.getStringExtra("feelInfor"));
        this.foodInfor.setTime(intent.getStringExtra("dateInfor"));
        this.foodInfor.setImage(Uri.parse(intent.getStringExtra("imageInfor")));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
