package com.course.msp.ui.meal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Spinner;

import android.widget.TextView;

import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.course.msp.MainActivity;
import com.course.msp.R;
import com.course.msp.contents.MyContentProvider;
import com.course.msp.controller.MapActicity;
import com.course.msp.databinding.ActivityMainBinding;
import com.course.msp.domain.dto.FoodDto;
import com.course.msp.domain.dto.FoodInfor;
import com.course.msp.repository.FoodInformationRepository;
import com.course.msp.repository.FoodRepository;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class InputActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button btnCamera, positionButton;
    private ImageView imageView;
    private Uri photoUri;
    private static final String RESOURCE = "com.course.msp.contents.MyContentProvider";
    private DatePickerDialog.OnDateSetListener callbackMethodDate;
    private TimePickerDialog.OnTimeSetListener callbackMethodTime;
    private String date, time;
    private String resultTime;
    private Spinner spinner;
    private static final String[] mealTime = {"선택하세요", "아침", "점심", "저녁"};

    public ArrayList<FoodDto> getFoodDto(){
        return FoodRepository.getFoods();
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
        setContentView(R.layout.input_meal);


        this.InitializeListenerDate();
        this.InitializeListenerTime();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spinner = (Spinner) this.findViewById(R.id.spinner1);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mealTime);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time = mealTime[i];
                resultTime = "[" + time + "] " + date;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnCamera = (Button) findViewById(R.id.btn_UploadImage);
        imageView = (ImageView) findViewById(R.id.foodImageView);
        btnCamera.setOnClickListener(this);

        Button posButton = (Button) findViewById(R.id.positionButton);
        posButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActicity.class);
                startActivity(intent);
            }
        });
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

    public void InitializeViewDate() {
        textView_date = (TextView) findViewById(R.id.textView_date);
    }

    public void InitializeListenerDate() {
        callbackMethodDate = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
                Log.d("info", "---> " + date);
            }
        };
    }

    public void OnClickHandler(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethodDate, 2022, 12, 5);
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

                Log.d("info", "On button");

                // 카메라 기능을 Intent
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d("Info", "Camera On!");
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


        FoodDto foodDto = new FoodDto(photoUri, resultTime);
        FoodInfor foodInfor = new FoodInfor();

        foodInfor.setFoodName(((EditText) findViewById(R.id.inputFoodName)).getText().toString());
        foodInfor.setFoodCount(((EditText) findViewById(R.id.inputFoodCount)).getText().toString());
        foodInfor.setFoodFeel(((EditText) findViewById(R.id.inputFoodFeel)).getText().toString());
        foodInfor.setTime(resultTime);
        foodInfor.setImage(photoUri);

        FoodInformationRepository.addFoodInfor(foodInfor);
        FoodRepository.addFood(foodDto);

        getContentResolver().insert(MyContentProvider.CONTENT_URI, addValues);
        Toast.makeText(getBaseContext(), "Record Added", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void openGallery(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    photoUri = data.getData();
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    imageView.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                try {
                    imageView.setImageURI(photoUri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // 카메라 촬영을 하면 이미지뷰에 사진 삽입
//        if(requestCode == 0 && resultCode == RESULT_OK) {
//            // 이미지뷰에 파일경로의 사진을 가져와 출력
//            imageView.setImageURI(photoUri);
//        } else if (requestCode == REQUEST_CODE) {
//            photoUri = data.getData();
//        }
//    }


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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
