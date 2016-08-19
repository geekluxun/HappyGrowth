package com.geekluxun.www.happygrowth.food.domain.usecase;

import com.geekluxun.www.happygrowth.UseCase;
import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.data.source.FoodRepository;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

import java.util.List;

/**
 * 获取记录 usecase
 */
public class GetFood  extends  UseCase<GetFood.RequestValues, GetFood.ResponseValue>{
    private  final FoodRepository mFoodRepository;

    public GetFood(FoodRepository foodRepository){
        mFoodRepository = foodRepository;
    }

    //用户通过此方法向Repository请求数据
    @Override
    protected void executeUseCase(RequestValues requestValues) {

        mFoodRepository.getFoods(requestValues.getDate(), requestValues.getAmountRange(), requestValues.getType(), new FoodDataSource.getFoodsCallback() {
            @Override
            public void onFoodLoaded(List<Food> foodList) {
                GetFood.ResponseValue responseValue = new GetFood.ResponseValue(foodList);
                getUseCaseCallback().onSuccess(responseValue);
            }
            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static  final class RequestValues implements UseCase.RequestValues {

        private  final String mDate;
        private  final FoodAmountRange mAmountRange;
        private  final String mType;

        public RequestValues(String date, FoodAmountRange amountRange, String type){
            mDate = date;
            mAmountRange = amountRange;
            mType = type;
        }

        public String getDate() {
            return mDate;
        }

        public FoodAmountRange getAmountRange() {
            return mAmountRange;
        }

        public String getType() {
            return mType;
        }

    }

    public static  final class ResponseValue implements  UseCase.ResponseValue {

        private  final List<Food> mList;

        public  ResponseValue(List<Food> list){
            mList = list;
        }

        public  List<Food> GetFoodList(){
            return mList;
        }
    }
}
