/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.geekluxun.www.happygrowth.food;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.IdlingResource;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.geekluxun.www.happygrowth.Injection;
import com.geekluxun.www.happygrowth.R;
import com.geekluxun.www.happygrowth.util.ActivityUtils;
import com.geekluxun.www.happygrowth.util.EspressoIdlingResource;


public class FoodActivity extends AppCompatActivity {

    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

    private DrawerLayout mDrawerLayout;

    private FoodPresenter mFoodsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

          // Set up the toolbar.
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//        ab.setDisplayHomeAsUpEnabled(true);
//
//        // Set up the navigation drawer.
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        if (navigationView != null) {
//            setupDrawerContent(navigationView);
//        }

        FoodFragment foodFragment =
                (FoodFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (foodFragment == null) {
            // Create the fragment
            foodFragment = FoodFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), foodFragment, R.id.fl_content);
        }

        // Create the presenter
        mFoodsPresenter = new FoodPresenter(
                Injection.provideUseCaseHandler(),
                foodFragment,
                Injection.provideAddFood(getApplicationContext())
                //Injection.provideCompleteFoods(getApplicationContext()),
//                Injection.provideActivateFood(getApplicationContext()),
//                Injection.provideClearCompleteFoods(getApplicationContext())
                );

        // Load previously saved state, if available.
        if (savedInstanceState != null) {
            FoodFilterType currentFiltering =
                    (FoodFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
            //mFoodsPresenter.setFiltering(currentFiltering);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //outState.putSerializable(CURRENT_FILTERING_KEY, mFoodsPresenter.getFiltering());

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        switch (menuItem.getItemId()) {
//                            case R.id.list_navigation_menu_item:
//                                // Do nothing, we're already on that screen
//                                break;
//                            case R.id.statistics_navigation_menu_item:
//                                Intent intent =
//                                        new Intent(FoodsActivity.this, StatisticsActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                break;
//                            default:
//                                break;
//                        }
//                        // Close the navigation drawer when an item is selected.
//                        menuItem.setChecked(true);
//                        mDrawerLayout.closeDrawers();
//                        return true;
//                    }
//                });
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
