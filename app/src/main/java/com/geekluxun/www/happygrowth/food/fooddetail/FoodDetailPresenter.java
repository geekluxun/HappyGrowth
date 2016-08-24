package com.geekluxun.www.happygrowth.food.fooddetail;

import com.geekluxun.www.happygrowth.UseCaseHandler;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.domain.usecase.DeleteFood;
import com.geekluxun.www.happygrowth.food.domain.usecase.GetFood;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;
import com.geekluxun.www.happygrowth.util.DateTimeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodDetailPresenter implements FoodDetailContract.Presenter {

    private UseCaseHandler mUseCaseHandler;
    private FoodDetailContract.View mFoodDetailView;
    private GetFood mGetFood;
    private DeleteFood mDeleteFood;
    private String mCurDate;
    private FoodAmountRange mCurAmountRange;
    private String mType;


    public FoodDetailPresenter(UseCaseHandler useCaseHandler,
                               FoodDetailContract.View view,
                               GetFood getFood,
                               DeleteFood deleteFood){
        this.mUseCaseHandler = useCaseHandler;
        this.mFoodDetailView = view;
        this.mGetFood = getFood;
        this.mDeleteFood = deleteFood;
        //此时Presenter已创建，传给view
        mFoodDetailView.setPresenter(this);
    }


    @Override
    public void getFoodDetail() {

        if (mCurDate == null || mType == null){
            return;
        }

        mUseCaseHandler.execute(mGetFood,
                                new GetFood.RequestValues(mCurDate, mCurAmountRange, mType),
                                new GetFood.UseCaseCallback<GetFood.ResponseValue>(){

            @Override
            public void onSuccess(GetFood.ResponseValue response) {
                List<Food> foodList = response.GetFoodList();
                int amountMilk = 0;
                int amountWater = 0;
                for (Food food :foodList) {
                    if (food.getType().equals("牛奶")){
                        amountMilk += food.getAmount();
                    } else if (food.getType().equals("水")){
                        amountWater += food.getAmount();
                    }
                }

                mFoodDetailView.showMilkAmountToday(amountMilk);
                mFoodDetailView.showWaterAmonutToday(amountWater);
                mFoodDetailView.showFoodDetail(foodList); //显示记录列表
            }

            @Override
            public void onError() {
                List<Food> foodList = new ArrayList<Food>();
                mFoodDetailView.showFoodDetail(foodList);
                mFoodDetailView.showNoRecord();
            }
        });
    }


    @Override
    public void selectFoodDate() {
        mFoodDetailView.showDatePickerDialog();
    }

    @Override
    public void deleteFood(final Food food) {

        mFoodDetailView.showDeleteRecordDialog(new FoodDetailContract.FoodDelDigCallback(){

            @Override
            public void onClickOk() {
                mUseCaseHandler.execute(mDeleteFood, new DeleteFood.RequestValues(food), new DeleteFood.UseCaseCallback<DeleteFood.ResponseValue>(){

                    @Override
                    public void onSuccess(DeleteFood.ResponseValue response) {
                        mFoodDetailView.showDeleteRecordSuccess();
                        getFoodDetail();//重新查询
                    }

                    @Override
                    public void onError() {

                    }
                });
            }

            @Override
            public void onClickCancel() {

            }
        });
    }

    @Override
    public void start() {
        Date date = new Date();
        DateFormat dateFormat  = new SimpleDateFormat(DateTimeUtils.DateFormat1);
        String dateStr = dateFormat.format(date);

        mFoodDetailView.showSearchCondition(dateStr,"全部","全部");

        setCurSearchCondition(dateStr, FoodAmountRange.ALL, "全部");
        getFoodDetail();
    }

    /**
     * 设置当前的查询条件
     */
    @Override
    public void setCurSearchCondition(String date, FoodAmountRange amountRange, String type){
        mCurDate = date;
        mCurAmountRange  = amountRange;
        mType = type;
    }
}
