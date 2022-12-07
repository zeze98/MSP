package com.course.msp.controller;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.msp.R;
import com.course.msp.domain.dto.FoodDto;
import com.course.msp.domain.dto.FoodInfor;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText;

        MyViewHolder(View view) {
            super(view);
            myText = view.findViewById(R.id.dateView);
        }
    }

    private ArrayList<FoodInfor> myFoodList;

    public MyAdapter(Context mContext, ArrayList<FoodInfor> foods){
        this.mContext = mContext;
        this.myFoodList = foods;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.show_meal_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.myText.setText(myFoodList.get(position).getTime());

        myViewHolder.myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(mContext, InforActivity.class);

                FoodInfor foodInfor = myFoodList.get(position);

                Log.d("Info", foodInfor.getFoodName());
                Log.d("Info", foodInfor.getFoodCount());
                Log.d("Info", foodInfor.getFoodFeel());
                Log.d("Info", foodInfor.getTime());
                Log.d("Info", foodInfor.getImage().toString());

                intent.putExtra("nameInfor", foodInfor.getFoodName());
                intent.putExtra("countInfor", foodInfor.getFoodCount());
                intent.putExtra("feelInfor", foodInfor.getFoodFeel());
                intent.putExtra("dateInfor", foodInfor.getTime());
                intent.putExtra("imageInfor", foodInfor.getImage().toString());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }
}
