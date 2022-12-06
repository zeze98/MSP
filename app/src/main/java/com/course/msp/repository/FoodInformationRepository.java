package com.course.msp.repository;

import com.course.msp.domain.dto.FoodInfor;

import java.util.ArrayList;

public class FoodInformationRepository {

    public static final ArrayList<FoodInfor> foodInfors = new ArrayList<>();

    public static ArrayList<FoodInfor> getFoodInfor(){
        return foodInfors;
    }

    public static void addFoodInfor(FoodInfor foodInfor){
        foodInfors.add(foodInfor);
    }


}
