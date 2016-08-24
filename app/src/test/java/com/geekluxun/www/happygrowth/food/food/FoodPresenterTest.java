package com.geekluxun.www.happygrowth.food.food;

import com.geekluxun.www.happygrowth.TestUseCaseScheduler;
import com.geekluxun.www.happygrowth.UseCaseHandler;
import com.geekluxun.www.happygrowth.food.data.source.FoodDataSource;
import com.geekluxun.www.happygrowth.food.data.source.FoodRepository;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.domain.usecase.AddFood;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mark on 2016/8/24.
 */
public class FoodPresenterTest {

    @Mock
    private FoodRepository mFoodRepository;

    @Mock
    private FoodContract.View mView;

    @Captor
    ArgumentCaptor<FoodDataSource.SaveFoodsCallback> mSaveFoodsCallbackArgumentCaptor;

    private FoodPresenter mFoodPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mView.isActive()).thenReturn(true);

        mFoodPresenter = givenFoodPresenter();
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void testStart() throws Exception {
//
//    }
//
//    @Test
//    public void testSetFoodTime() throws Exception {
//
//    }

    @Test
    public void testAddNewFoodRecord() throws Exception {
        Food food = new Food("2015", 100, "牛奶");
        mFoodPresenter.addNewFoodRecord(food.getTime(), food.getAmount() + "", food.getType());

        verify(mFoodRepository).saveFood(any(Food.class), mSaveFoodsCallbackArgumentCaptor.capture());

        verify(mView).showAddFoodRecordSuccess();
    }

    @Test
    public void testOpenFoodRecords() throws Exception {

    }

    @Test
    public void testSetFoodType() throws Exception {

    }

    private FoodPresenter givenFoodPresenter(){
        UseCaseHandler useCaseHandler = new UseCaseHandler(new TestUseCaseScheduler());
        AddFood addFood = new AddFood(mFoodRepository);
        return new FoodPresenter(useCaseHandler, mView, addFood);
    }
}