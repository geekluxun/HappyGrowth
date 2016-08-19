package com.geekluxun.www.happygrowth.food.domain.usecase;

import com.geekluxun.www.happygrowth.UseCase;
import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.data.source.FoodRepository;
import com.geekluxun.www.happygrowth.food.domain.model.Food;

/**
 * 删除一条记录 usecase
 */
public class DeleteFood extends  UseCase<DeleteFood.RequestValues, DeleteFood.ResponseValue>{
    private  final FoodRepository mFoodRepository;

    public DeleteFood(FoodRepository foodRepository){
        mFoodRepository = foodRepository;
    }

    //用户通过此方法向Repository请求数据
    @Override
    protected void executeUseCase(RequestValues requestValues) {

        mFoodRepository.deleteFood(requestValues.getFood(), new FoodDataSource.DeleteFoodCallback() {

            @Override
            public void onFoodDeleted() {
                getUseCaseCallback().onSuccess(new DeleteFood.ResponseValue());
            }

            @Override
            public void onError() {

            }
        });

    }

    public static  final class RequestValues implements UseCase.RequestValues {

        private  final  Food mFood;

        public RequestValues(Food food){
            mFood = food;
        }

        public Food getFood() {
            return mFood;
        }

    }

    public static  final class ResponseValue implements  UseCase.ResponseValue {

    }
}
