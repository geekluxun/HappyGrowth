package com.geekluxun.www.happygrowth;

import android.content.Context;
import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.data.FakeFoodRemoteDataSource;
import com.geekluxun.www.happygrowth.food.data.source.FoodRepository;
import com.geekluxun.www.happygrowth.food.data.source.local.FoodLocalDataSource;
import com.geekluxun.www.happygrowth.food.domain.usecase.AddFood;
import com.geekluxun.www.happygrowth.food.domain.usecase.DeleteFood;
import com.geekluxun.www.happygrowth.food.domain.usecase.GetFood;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of mock implementations for
 * {@link com.geekluxun.www.happygrowth.food.data.source.FoodDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static FoodRepository provideFoodRepository(@NonNull Context context) {
        checkNotNull(context);
        return FoodRepository.getInstance(FakeFoodRemoteDataSource.getInstance(),
                FoodLocalDataSource.getInstance(context));
    }

    public static AddFood provideAddFood(@NonNull Context context) {
        return  new AddFood(provideFoodRepository(context));
    }

    public static GetFood provideGetFood(@NonNull Context context) {
        return  new GetFood(provideFoodRepository(context));
    }

    public static DeleteFood provideDeleteFood(@NonNull Context context) {
        return  new DeleteFood(provideFoodRepository(context));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

}
