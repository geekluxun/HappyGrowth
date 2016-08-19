package com.geekluxun.www.happygrowth.food.food;

import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.UseCase;
import com.geekluxun.www.happygrowth.UseCaseHandler;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.domain.usecase.AddFood;

import static com.google.common.base.Preconditions.checkNotNull;

public class FoodPresenter implements FoodContract.Presenter {

    private final FoodContract.View mFoodsView;
    private final AddFood mAddFood;
    private boolean mFirstLoad = true;

    private final UseCaseHandler mUseCaseHandler;

    public FoodPresenter(@NonNull UseCaseHandler useCaseHandler,
            @NonNull FoodContract.View tasksView, @NonNull AddFood addFood) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "usecaseHandler cannot be null");
        mFoodsView = checkNotNull(tasksView, "tasksView cannot be null!");
        mAddFood = checkNotNull(addFood, "getFood cannot be null!");
        mFoodsView.setPresenter(this);
    }

    @Override
    public void start() {
        setFoodType("牛奶"); //默认选择牛奶
    }

    @Override
    public void setFoodTime() {

    }

    @Override
    public void addNewFoodRecord(String time, String amountStr, String type) {
        int amount = 0;
        try {
            amount = Integer.valueOf(amountStr);
        } catch (Exception e){
            mFoodsView.showFoodAmountError();
            return;
        }

        if (time == null || time.equals("")){
            mFoodsView.showFoodDateError();
            return;
        }

        Food food = new Food(time, amount, type);
        mUseCaseHandler.execute(mAddFood, new AddFood.RequestValues(food),
                new UseCase.UseCaseCallback<AddFood.ResponseValue>() {
                    @Override
                    public void onSuccess(AddFood.ResponseValue response) {
                        //mAddTaskView.showTasksList();
                        AddFood.ResponseValue responseValue = response;
                        mFoodsView.showAddFoodRecordSuccess();
                    }

                    @Override
                    public void onError() {
                        //showSaveError();
                    }
                });
    }

    @Override
    public void openFoodRecords() {
        mFoodsView.showFoodRecords();
    }


    @Override
    public void setFoodType(String type) {
        mFoodsView.showFoodType(type);
    }
}
