package com.geekluxun.www.happygrowth.food.data.source;

import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class FoodRepositoryTest {

    @Mock
    private FoodDataSource mLocalDataSource;
    @Mock
    private FoodDataSource mRemoteDateSource;
    @Mock
    private FoodDataSource.DeleteFoodCallback mDeleteFoodCallback;
    @Mock
    private FoodDataSource.SaveFoodsCallback mSaveFoodsCallback;
    @Mock
    private FoodDataSource.getFoodsCallback mGetFoodsCallback;


    private FoodRepository mFoodRepository;


    @Captor
    private ArgumentCaptor<FoodDataSource.DeleteFoodCallback> mDeleteFoodCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<FoodDataSource.SaveFoodsCallback> mSaveFoodsCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<FoodDataSource.getFoodsCallback> mGetFoodsCallbackArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mFoodRepository = FoodRepository.getInstance(mRemoteDateSource, mLocalDataSource);
    }

    @After
    public void tearDown() throws Exception {
        FoodRepository.destroyInstance();
    }

    @Test
    public void testGetInstance() throws Exception {

    }

    @Test
    public void testDestroyInstance() throws Exception {

    }

    @Test
    public void testSaveFood() throws Exception {

    }

    @Test
    public void testGetFoods() throws Exception {
        mFoodRepository.getFoods("2015", FoodAmountRange.ALL, "牛奶", mGetFoodsCallback);
        verify(mLocalDataSource).getFoods(any(String.class), any(FoodAmountRange.class), any(String.class),mGetFoodsCallbackArgumentCaptor.capture() );

    }

    @Test
    public void testDeleteFood() throws Exception {
        Food food = new Food("11", 11, "33");
        mFoodRepository.deleteFood(food, mDeleteFoodCallback);
        verify(mLocalDataSource).deleteFood(eq(food),mDeleteFoodCallbackArgumentCaptor.capture());
    }
}