package com.geekluxun.www.happygrowth.food.food;

import com.geekluxun.www.happygrowth.BasePresenter;
import com.geekluxun.www.happygrowth.BaseView;

public interface FoodContract {

    interface View extends BaseView<Presenter> {
        //进食时间
        void showFoodTime();
        //增加一条进食记录
        void showAddOneFoodRecord();
        //进食记录
        void showFoodRecords();
        //食物类型
        void showFoodType(String type);
        //是否有效
        boolean isActive();
        //食物类型 从view 到 presenter 转换
        String getFoodType();

        void showAddFoodRecordSuccess();
        //食物数量错误
        void showFoodAmountError();
        //食物日期错误
        void showFoodDateError();
    }

    interface Presenter extends BasePresenter {
        //设置进食时间
        void setFoodTime();
        //增加一条进食记录
        void addNewFoodRecord(String time, String amount, String type);
        //打开进食记录
        void openFoodRecords();

        void setFoodType(String type);
    }
}
