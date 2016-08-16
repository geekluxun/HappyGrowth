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

package com.geekluxun.www.happygrowth.food.data.source;

import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.food.domain.model.Food;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
public class FoodRepository implements FoodDataSource {

    private static FoodRepository INSTANCE = null;

    private final FoodDataSource mFoodsRemoteDataSource;

    private final FoodDataSource mFoodsLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Food> mCachedFoods;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private FoodRepository(@NonNull FoodDataSource tasksRemoteDataSource,
                            @NonNull FoodDataSource tasksLocalDataSource) {
        mFoodsRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mFoodsLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksRemoteDataSource the backend data source
     * @param tasksLocalDataSource  the device storage data source
     * @return the {@link FoodRepository} instance
     */
    public static FoodRepository getInstance(FoodDataSource tasksRemoteDataSource,
                                              FoodDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new FoodRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(FoodDataSource, FoodDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void saveFood(@NonNull Food food, @NonNull SaveFoodsCallback callback) {
        checkNotNull(food);
        //saveFoodFromRemoteDataSource(food, callback);
        mFoodsLocalDataSource.saveFood(food, callback);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedFoods == null) {
            mCachedFoods = new LinkedHashMap<>();
        }
    }

    private void saveFoodFromRemoteDataSource(Food food, @NonNull final SaveFoodsCallback callback) {
        mFoodsRemoteDataSource.saveFood(food, new SaveFoodsCallback() {
            @Override
            public void onFoodSaved(Food food) {
                callback.onFoodSaved(food);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
