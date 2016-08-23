package com.geekluxun.www.happygrowth;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TimePicker;

import com.geekluxun.www.happygrowth.food.food.FoodActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by mark on 2016/8/23.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FoodActivityTest {

    @Rule
    public ActivityTestRule mFoodActivityActivityTestRule  = new ActivityTestRule<FoodActivity>(FoodActivity.class){
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
        }
    };

    @Test
    public  void showFoodTimeTest(){
        onView(allOf(withId(R.id.btn_add_foodtime), withText("添加时间"))).perform(click());
        onView(allOf(withId(R.id.btn_add_foodtime), withText("添加时间"))).perform(setTime(11,10));
        //onView(withClassName(Matchers.equalTo((TimePickerDialog.class.getName())))).perform(click());
    }


    /**
     * 自定义 ViewAction
     * @param hour
     * @param minute
     * @return
     */
    public static ViewAction setTime(final int hour, final int minute) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                TimePicker tp = (TimePicker) view;
                tp.setCurrentHour(hour);
                tp.setCurrentMinute(minute);
            }
            @Override
            public String getDescription() {
                return "Set the passed time into the TimePicker";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(TimePicker.class);
            }
        };
    }
}
