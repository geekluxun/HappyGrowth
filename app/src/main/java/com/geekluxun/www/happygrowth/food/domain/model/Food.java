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

package com.geekluxun.www.happygrowth.food.domain.model;

import android.support.annotation.Nullable;

/**
 * Immutable model class for a Food.
 */
public final class Food {

    @Nullable
    private final String mTime;

    @Nullable
    private final int mAmount;

    @Nullable
    private final String mType;


    /**
     *
     * @param time
     * @param amount
     * @param type
     */
    public Food(@Nullable String time, @Nullable int amount, @Nullable String type) {
        mTime = time;
        mAmount = amount;
        mType = type;
    }

    @Nullable
    public String getTime() {
        return mTime;
    }

    @Nullable
    public int getAmount() {
        return mAmount;
    }

    @Nullable
    public String getType() {
        return mType;
    }

}
