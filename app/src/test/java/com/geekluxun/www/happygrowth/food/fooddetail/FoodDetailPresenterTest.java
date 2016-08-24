package com.geekluxun.www.happygrowth.food.fooddetail;

import com.geekluxun.www.happygrowth.TestUseCaseScheduler;
import com.geekluxun.www.happygrowth.UseCaseHandler;
import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.data.source.FoodRepository;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.domain.usecase.DeleteFood;
import com.geekluxun.www.happygrowth.food.domain.usecase.GetFood;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mark on 2016/8/24.
 */
public class FoodDetailPresenterTest {

    @Mock
    private FoodRepository mFoodRepository;

    @Mock
    private FoodDetailContract.View mView;

    @Captor
    private ArgumentCaptor<FoodDataSource.getFoodsCallback> mGetFoodsCallbackArgumentCaptor;

    private FoodDetailPresenter mFoodDetailPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mView.isActive()).thenReturn(true);

        mFoodDetailPresenter = givenPresenter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetFoodDetail() throws Exception {
        mFoodDetailPresenter.setCurSearchCondition("2016年08月11日 18:22:33",FoodAmountRange.ALL, "牛奶");
        mFoodDetailPresenter.getFoodDetail();

        verify(mFoodRepository).getFoods(eq("2016年08月11日 18:22:33"),any(FoodAmountRange.class),any(String.class),mGetFoodsCallbackArgumentCaptor.capture());
        mGetFoodsCallbackArgumentCaptor.getValue().onDataNotAvailable();
        List<Food> foodList = new ArrayList<Food>();
        foodList.add(new Food("33", 11, "牛奶"));
        foodList.add(new Food("33", 23, "牛奶"));
        foodList.add(new Food("33", 23, "牛奶"));

        mGetFoodsCallbackArgumentCaptor.getValue().onFoodLoaded(foodList);
        verify(mView).showMilkAmountToday(eq(57));
        verify(mView).showNoRecord();
    }

    @Test
    public void testSelectFoodDate() throws Exception {

    }

    @Test
    public void testDeleteFood() throws Exception {

    }

    @Test
    public void testStart() throws Exception {

    }

    @Test
    public void testSetCurSearchCondition() throws Exception {

    }

    /**
     *
     * @return
     */
    private FoodDetailPresenter givenPresenter() {

        UseCaseHandler useCaseHandler = new UseCaseHandler(new TestUseCaseScheduler());
        GetFood getFood = new GetFood(mFoodRepository);
        DeleteFood deleteFood = new DeleteFood(mFoodRepository);

        return new FoodDetailPresenter(useCaseHandler,mView,getFood,deleteFood);
    }
}