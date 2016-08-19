/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.geekluxun.www.happygrowth.food.data.source.remote;

import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

/**
 * Implementation of the data source that adds a latency simulating network.
 */
public class FoodRemoteDataSource implements FoodDataSource {

    private static FoodRemoteDataSource INSTANCE;

//    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;
//
//    private static final Map<String, Food> TASKS_SERVICE_DATA;
//
////    static {
////        TASKS_SERVICE_DATA = new LinkedHashMap<>(2);
////        addFood("Build tower in Pisa", "Ground looks good, no foundation work required.");
////        addFood("Finish bridge in Tacoma", "Found awesome girders at half the cost!");
////    }

    public static FoodRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FoodRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private FoodRemoteDataSource() {}



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
