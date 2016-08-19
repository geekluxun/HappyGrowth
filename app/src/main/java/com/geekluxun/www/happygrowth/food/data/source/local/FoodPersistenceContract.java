

package com.geekluxun.www.happygrowth.food.data.source.local;

import android.provider.BaseColumns;

/**
 * Food 本地数据表字段
 */
public final class FoodPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FoodPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME = "food";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_TYPE = "type";
    }
}
