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

package com.geekluxun.www.happygrowth.food;

import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.UseCase;
import com.geekluxun.www.happygrowth.UseCaseHandler;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.domain.usecase.AddFood;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link FoodFragment}), retrieves the data and updates the
 * UI as required.
 */
public class FoodPresenter implements FoodContract.Presenter {


    private final FoodContract.View mFoodsView;
    private final AddFood mAddFood;
//    private final CompleteFood mCompleteFood;
//    private final ActivateFood mActivateFood;
//    private final ClearCompleteFoods mClearCompleteFoods;

    private FoodFilterType mCurrentFiltering = FoodFilterType.ALL_TASKS;

    private boolean mFirstLoad = true;

    private final UseCaseHandler mUseCaseHandler;

    public FoodPresenter(@NonNull UseCaseHandler useCaseHandler,
            @NonNull FoodContract.View tasksView, @NonNull AddFood addFood) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "usecaseHandler cannot be null");
        mFoodsView = checkNotNull(tasksView, "tasksView cannot be null!");
        mAddFood = checkNotNull(addFood, "getFood cannot be null!");
//        mCompleteFood = checkNotNull(completeFood, "completeFood cannot be null!");
//        mActivateFood = checkNotNull(activateFood, "activateFood cannot be null!");
//        mClearCompleteFoods = checkNotNull(clearCompleteFoods,
//                "clearCompleteFoods cannot be null!");


        mFoodsView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void setFoodTime() {

    }

    @Override
    public void addNewFoodRecord(Food food) {

        mUseCaseHandler.execute(mAddFood, new AddFood.RequestValues(food),
                new UseCase.UseCaseCallback<AddFood.ResponseValue>() {
                    @Override
                    public void onSuccess(AddFood.ResponseValue response) {
                        //mAddTaskView.showTasksList();
                        AddFood.ResponseValue responseValue = response;
                    }

                    @Override
                    public void onError() {
                        //showSaveError();
                    }
                });
    }

    @Override
    public void openFoodRecords() {

    }
}
