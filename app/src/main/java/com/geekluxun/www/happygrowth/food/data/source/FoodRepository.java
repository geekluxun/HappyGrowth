

package com.geekluxun.www.happygrowth.food.data.source;

import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 向Domain层暴露的获取数据源的接口
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
    }

    @Override
    public void getFoods(String date, FoodAmountRange amountRange, String type, @NonNull getFoodsCallback callback) {
        mFoodsLocalDataSource.getFoods(date, amountRange, type, callback);
    }

    @Override
    public void deleteFood(Food food, DeleteFoodCallback callback) {
        mFoodsLocalDataSource.deleteFood(food, callback);
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
