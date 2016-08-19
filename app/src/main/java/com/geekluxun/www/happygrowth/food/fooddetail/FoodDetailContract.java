package com.geekluxun.www.happygrowth.food.fooddetail;

import com.geekluxun.www.happygrowth.BasePresenter;
import com.geekluxun.www.happygrowth.BaseView;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

import java.util.List;

public interface FoodDetailContract {

    interface View extends BaseView<Presenter>{
        void showFoodDetail(List<Food> foods);
        void showMilkAmountToday(int amount);
        void showWaterAmonutToday(int amount);
        void showSearchCondition(String date, String amount, String type);
        void showDatePickerDialog();
        void showNoRecord();
        void showDeleteRecordSuccess();
        void showDeleteRecordDialog(FoodDelDigCallback callback);
//        String getFoodDate();
//        String getFoodAmountRange();
//        String getFoodType();
    }

    interface Presenter extends BasePresenter{
        void getFoodDetail();
        void setCurSearchCondition(String date, FoodAmountRange amountRange, String type);
        void init();
        void selectFoodDate();
        void deleteFood(Food food);
    }

    interface FoodDelDigCallback {
        void onClickOk();
        void onClickCancel();
    }
}
