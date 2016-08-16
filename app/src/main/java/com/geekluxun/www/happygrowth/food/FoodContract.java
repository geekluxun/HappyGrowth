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

import com.geekluxun.www.happygrowth.BasePresenter;
import com.geekluxun.www.happygrowth.BaseView;
import com.geekluxun.www.happygrowth.food.domain.model.Food;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface FoodContract {

    interface View extends BaseView<Presenter> {
        //进食时间
        void showFoodTime();
        //增加一条进食记录
        void showAddOneFoodRecord();
        //进食记录
        void showFoodRecords();
        //食物类型
        void showFoodType();
        //是否有效
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        //设置进食时间
        void setFoodTime();
        //增加一条进食记录
        void addNewFoodRecord(Food food);
        //打开进食记录
        void openFoodRecords();
    }
}
