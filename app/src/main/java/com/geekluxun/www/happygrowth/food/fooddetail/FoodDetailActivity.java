package com.geekluxun.www.happygrowth.food.fooddetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.geekluxun.www.happygrowth.Injection;
import com.geekluxun.www.happygrowth.R;
import com.geekluxun.www.happygrowth.util.ActivityUtils;

public class FoodDetailActivity extends AppCompatActivity {
    FoodDetailPresenter mFoodDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        FoodDetailFragment foodDetailFragment = (FoodDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fg_content);
        if (foodDetailFragment == null){
            foodDetailFragment = FoodDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),foodDetailFragment, R.id.fg_content);
        }

        mFoodDetailPresenter = new FoodDetailPresenter(Injection.provideUseCaseHandler(),
                foodDetailFragment,
                Injection.provideGetFood(getApplicationContext()),
                Injection.provideDeleteFood(getApplicationContext()));
    }
}
