package com.geekluxun.www.happygrowth.food.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.data.source.local.FoodPersistenceContract.FoodEntry;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 本地数据库方式实现的数据源
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

    @Override
    public void getFoods(String date, @NonNull FoodAmountRange amountRange, String type, @NonNull getFoodsCallback callback) {

        List<Food> foodList = new ArrayList<Food>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                FoodEntry.COLUMN_NAME_TIME,
                FoodEntry.COLUMN_NAME_AMOUNT,
                FoodEntry.COLUMN_NAME_TYPE,
        };

        StringBuilder where = new StringBuilder();

        switch (amountRange)
        {
            case BELOW_30ML:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + "<=" + 30);
                break;
            case BETWEEN_30_TO_60ML:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + ">" + 30 +
                        " and " + FoodEntry.COLUMN_NAME_AMOUNT + "<=" + 60);
                break;
            case BETWEEN_60_TO_90ML:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + ">" + 60 +
                        " and " + FoodEntry.COLUMN_NAME_AMOUNT + "<=" + 90);
                break;
            case BETWEEN_90_TO_120ML:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + ">" + 90 +
                        " and " + FoodEntry.COLUMN_NAME_AMOUNT + "<=" + 120);
                break;
            case BETWEEN_120_TO_150ML:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + ">" + 120 +
                        " and " + FoodEntry.COLUMN_NAME_AMOUNT + "<=" + 150);
                break;
            case BETWEEN_150_TO_180ML:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + ">" + 150 +
                        " and " + FoodEntry.COLUMN_NAME_AMOUNT + "<=" + 180);
                break;
            case UP_180ML:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + ">" + 180);
                break;
            case ALL:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + ">" + 0);
                break;
            default:
                where.append(" where " + FoodEntry.COLUMN_NAME_AMOUNT + ">" + 0);
                break;
        }

        //日期
        where.append(" and " + FoodEntry.COLUMN_NAME_TIME + " like " + "\"" + date + "%" + "\"");
        //类型
        if (!type.equals("全部")){
            where.append(" and " + FoodEntry.COLUMN_NAME_TYPE + " like " + "\"" + type + "\"");
        }

        where.append(" order by " + FoodEntry.COLUMN_NAME_TIME + " desc");

        Cursor c = db.rawQuery("select * from food" + where, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String time = c.getString(c.getColumnIndexOrThrow(FoodEntry.COLUMN_NAME_TIME));
                int amount = c.getInt(c.getColumnIndexOrThrow(FoodEntry.COLUMN_NAME_AMOUNT));
                String type2 =  c.getString(c.getColumnIndexOrThrow(FoodEntry.COLUMN_NAME_TYPE));
                Food food = new Food(time, amount, type2);
                foodList.add(food);
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();

        if (foodList.isEmpty()) {
            // This will be called if the table is new or just empty.
            callback.onDataNotAvailable();
        } else {
            callback.onFoodLoaded(foodList);
        }
    }

    @Override
    public void deleteFood(Food food, DeleteFoodCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String sql = "DELETE FROM "+ FoodEntry.TABLE_NAME +
                    " WHERE " + FoodEntry.COLUMN_NAME_TIME + " = " + "\"" + food.getTime() + "\""
                    + " AND " + FoodEntry.COLUMN_NAME_AMOUNT + " = " + food.getAmount()
                    + " AND " + FoodEntry.COLUMN_NAME_TYPE + " = " + "\"" + food.getType() + "\"";

        try {
            db.execSQL(sql);
        } catch (SQLException e){
            callback.onError();
            return;
        } finally {
            callback.onFoodDeleted();
        }

    }
}
