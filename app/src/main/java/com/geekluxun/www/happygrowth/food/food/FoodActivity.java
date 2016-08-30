package com.geekluxun.www.happygrowth.food.food;

import android.os.Bundle;

import com.geekluxun.www.happygrowth.Injection;
import com.geekluxun.www.happygrowth.R;
import com.geekluxun.www.happygrowth.util.ActivityUtils;
import com.zhy.autolayout.AutoLayoutActivity;


public class FoodActivity extends AutoLayoutActivity {

    private FoodPresenter mFoodsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        FoodFragment foodFragment =
                (FoodFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (foodFragment == null) {
            // Create the fragment
            foodFragment = FoodFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), foodFragment, R.id.fl_content);
        }

        // Create the presenter
        mFoodsPresenter = new FoodPresenter(
                Injection.provideUseCaseHandler(),
                foodFragment,
                Injection.provideAddFood(getApplicationContext())
                );    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
