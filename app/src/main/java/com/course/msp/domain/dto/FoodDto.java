package com.course.msp.domain.dto;

import android.net.Uri;

public class FoodDto {

    private Uri imageUrl;
    private String dateTime;

    public FoodDto(Uri url, String time){
        this.imageUrl = url;
        this.dateTime = time;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
