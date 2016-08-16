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

package com.geekluxun.www.happygrowth.food.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.data.source.local.FoodPersistenceContract.FoodEntry;
import com.geekluxun.www.happygrowth.food.domain.model.Food;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation of a data source as a db.
 */
public class FoodLocalDataSource implements FoodDataSource {

    private static FoodLocalDataSource INSTANCE;

    private FoodDbHelper mDbHelper;

    // Prevent direct instantiation.
    private FoodLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new FoodDbHelper(context);
    }

    public static FoodLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new FoodLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void saveFood(@NonNull Food food, @NonNull SaveFoodsCallback callback) {
        checkNotNull(food);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodEntry.COLUMN_NAME_TIME, food.getTime());
        values.put(FoodEntry.COLUMN_NAME_AMOUNT, food.getAmount());
        values.put(FoodEntry.COLUMN_NAME_TYPE, food.getType());

        db.insert(FoodEntry.TABLE_NAME, null, values);

        db.close();
        //保存数据成功回调
        callback.onFoodSaved(food);
    }
}
