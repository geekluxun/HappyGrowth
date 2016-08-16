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

package com.geekluxun.www.happygrowth.data;

import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.domain.model.Food;

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
}
