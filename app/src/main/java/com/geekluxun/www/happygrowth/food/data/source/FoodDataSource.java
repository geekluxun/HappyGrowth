

package com.geekluxun.www.happygrowth.food.data.source;

import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

import java.util.List;

/**
 * 数据源总接口
 */
public interface FoodDataSource {

    interface SaveFoodsCallback {

        void onFoodSaved(Food food);

        void onDataNotAvailable();
    }

    interface getFoodsCallback {

        void onFoodLoaded (List<Food> foodList);
        void onDataNotAvailable();
    }

    interface  DeleteFoodCallback {
        void onFoodDeleted();
        void onError();
    }

    void saveFood(@NonNull Food food, @NonNull SaveFoodsCallback callback);

    void getFoods(String date, FoodAmountRange amountRange, String type, @NonNull getFoodsCallback callback);

    void deleteFood(Food food , DeleteFoodCallback callback);
}
