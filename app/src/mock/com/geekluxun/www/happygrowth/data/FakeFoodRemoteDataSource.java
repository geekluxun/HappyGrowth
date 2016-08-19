

package com.geekluxun.www.happygrowth.data;

import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
public class FakeFoodRemoteDataSource implements FoodDataSource {

    private static FakeFoodRemoteDataSource INSTANCE;

    private static final Map<String, Food> TASKS_SERVICE_DATA = new LinkedHashMap<>();

    // Prevent direct instantiation.
    private FakeFoodRemoteDataSource() {}

    public static FakeFoodRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeFoodRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void saveFood(@NonNull Food food, @NonNull SaveFoodsCallback callback) {

    }

    @Override
    public void getFoods(String date, FoodAmountRange amountRange, String type, @NonNull getFoodsCallback callback) {

    }

    @Override
    public void deleteFood(Food food, DeleteFoodCallback callback) {

    }
}
