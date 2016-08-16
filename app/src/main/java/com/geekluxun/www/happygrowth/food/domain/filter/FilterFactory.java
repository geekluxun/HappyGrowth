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

package com.geekluxun.www.happygrowth.food.domain.filter;

import com.geekluxun.www.happygrowth.food.FoodFilterType;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory of {@link FoodFilter}s.
 */
public class FilterFactory {

    private static final Map<FoodFilterType,FoodFilter> mFilters = new HashMap<>();

    public FilterFactory() {
        //mFilters.put(FoodFilterType.ALL_TASKS, FoodFilter());
       // mFilters.put(FoodFilterType.ACTIVE_TASKS, new ActiveTaskFilter());
       // mFilters.put(FoodFilterType.COMPLETED_TASKS, new CompleteTaskFilter());
    }

    public FoodFilter create(FoodFilterType filterType) {
        return mFilters.get(filterType);
    }
}
