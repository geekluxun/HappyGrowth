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

package com.geekluxun.www.happygrowth.food.domain.usecase;

import android.support.annotation.NonNull;
import com.geekluxun.www.happygrowth.UseCase;
import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.data.source.FoodRepository;
import com.geekluxun.www.happygrowth.food.domain.model.Food;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Marks a task as active (not completed yet).
 */
public class AddFood extends UseCase<AddFood.RequestValues, AddFood.ResponseValue> {

    private final FoodRepository mFoodsRepository;

    public AddFood(@NonNull FoodRepository foodRepository) {
        mFoodsRepository = checkNotNull(foodRepository, "tasksRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        Food food = values.getFood();
        mFoodsRepository.saveFood(food, new FoodDataSource.SaveFoodsCallback() {
            @Override
            public void onFoodSaved(Food food) {
                ResponseValue responseValue = new ResponseValue();
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final Food mFood;

        public RequestValues(@NonNull Food food) {
            mFood = checkNotNull(food, "Food cannot be null!");
        }

        public Food getFood() {
            return mFood;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue { }
}
