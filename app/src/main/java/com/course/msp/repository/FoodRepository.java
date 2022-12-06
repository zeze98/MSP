package com.course.msp.repository;


import com.course.msp.controller.Foods;
import com.course.msp.domain.dto.FoodDto;

import java.util.ArrayList;

public class FoodRepository {

    public static ArrayList<FoodDto> foods = new ArrayList<>();

    public static void addFood(FoodDto foodDto){
        foods.add(foodDto);
    }

    public static ArrayList<FoodDto> getFoods(){
        return foods;
    }

}
