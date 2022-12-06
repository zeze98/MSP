package com.course.msp.controller;

public class Foods {

    public String ateDate;
    public int imageId;

    class Food{

        public String ateDate;
        public int imageId;

        public Food(String date, int imageId){
            this.ateDate = date;
            this.imageId = imageId;
        }

        public String getAteDate() {
            return ateDate;
        }

        public int getImageId(){
            return imageId;
        }
    }


}
