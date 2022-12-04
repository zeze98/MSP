package com.course.msp.ui.meal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.course.msp.MainActivity;
import com.course.msp.R;
import com.course.msp.contents.MyContentProvider;
import com.course.msp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private Button btnCamera;
    private ImageView imageView;
    private Uri photoUri;
    static final int REQUEST_CODE = 1;
    private static final String RESOURCE = "com.course.msp.contents.MyContentProvider";
    private DatePickerDialog.OnDateSetListener callbackMethodDate;
    private TimePickerDialog.OnTimeSetListener callbackMethodTime;
    private String date, time;
    private String resultTime;

    private void setDate(String date){
        this.date = date;
    }

    private void setTime(String time){
        this.time = time;
    }

    private void setResultTime(String date, String time){
        this.resultTime = date + time;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.InitializeListenerDate();
        this.InitializeListenerTime();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setContentView(R.layout.input_meal);

        btnCamera = (Button) findViewById(R.id.btn_UploadImage);
        imageView = (ImageView) findViewById(R.id.foodImageView);
        btnCamera.setOnClickListener(this);
    }

    public void InitializeListenerDate() {
        callbackMethodDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = changeMonthOfYear(monthOfYear);
                String newDate = year + "년" + (monthOfYear) + "월" + dayOfMonth + "일";
                setDate(newDate);
                Log.d("info", "---> " + date);
            }
        };
    }

    private int changeMonthOfYear(int month){
        return month+1;
    }

    public void OnClickHandler(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethodDate, 2022, 12, 1);
        Log.d("Info", "자, 열리기 시작합니다");
        dialog.show();
    }

    public void InitializeListenerTime() {
        callbackMethodTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String newTime = hourOfDay + "시" + minute + "분";
                setTime(newTime);
                Log.d("info", "---->" + time);

                setResultTime(date, time);
                Log.d("RESULT", "-->>" + resultTime);
            }
        };
    }
    public void OnClickTimeHandlerTime(View view) {
        TimePickerDialog dialog = new TimePickerDialog(this, callbackMethodTime, 8, 10, true);
        dialog.show();
    }

    @Override
    public void onClick(View view)    {
        switch (view.getId()) {
            // 카메라촬영 클릭 이벤트
            case R.id.btn_UploadImage:
                // 카메라 기능을 Intent
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 사진파일 변수 선언 및 경로세팅
                File photoFile = null;
                try { photoFile = createImageFile(); } catch (IOException ex) { }

                // 사진을 저장하고 이미지뷰에 출력
                if(photoFile != null) {
                    photoUri = FileProvider.getUriForFile(this, RESOURCE, photoFile);
                    Log.d("info", "File Save Directory : " + RESOURCE);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                    startActivityForResult(i, 0);
                }
                break;
        }
    }

    public void addFoodInformation(View view){
        ContentValues addValues = new ContentValues();
        addValues.put(MyContentProvider.NAME, ((EditText) findViewById(R.id.inputFoodName)).getText().toString());
        Log.d("DETAIL", ((EditText) findViewById(R.id.inputFoodName)).getText().toString());

        addValues.put(MyContentProvider.FOOD_COUNT, ((EditText) findViewById(R.id.inputFoodCount)).getText().toString());
        Log.d("DETAIL", ((EditText) findViewById(R.id.inputFoodCount)).getText().toString());

        addValues.put(MyContentProvider.FOOD_FEEL, ((EditText) findViewById(R.id.inputFoodFeel)).getText().toString());
        Log.d("DETAIL", ((EditText) findViewById(R.id.inputFoodFeel)).getText().toString());

        addValues.put(MyContentProvider.IMAGE, photoUri.toString());
        Log.d("DETAIL", photoUri.toString());

        addValues.put(MyContentProvider.EAT_TIME, resultTime);
        Log.d("DEATIL", resultTime);


        getContentResolver().insert(MyContentProvider.CONTENT_URI, addValues);
        Toast.makeText(getBaseContext(), "Record Added", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void openGallery(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)    {
        super.onActivityResult(requestCode, resultCode, data);

        // 카메라 촬영을 하면 이미지뷰에 사진 삽입
        if(requestCode == 0 && resultCode == RESULT_OK) {
            // 이미지뷰에 파일경로의 사진을 가져와 출력
            imageView.setImageURI(photoUri);
        } else if (requestCode == REQUEST_CODE) {
            photoUri = data.getData();
        }
    }


    // ImageFile의 경로를 가져올 메서드 선언
    private File createImageFile() throws IOException {
        // 파일이름을 세팅 및 저장경로 세팅
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_"; File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File StorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        return image;
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
