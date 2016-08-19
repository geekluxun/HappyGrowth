package com.geekluxun.www.happygrowth.food.domain.model;

import android.support.annotation.Nullable;

/**
 * 实体类
 */
public final class Food {

    @Nullable
    private final String mTime;

    @Nullable
    public final int mAmount;

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
