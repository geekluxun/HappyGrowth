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

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.geekluxun.www.happygrowth.R;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.util.DateTimeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Display a grid of {@link Food}s. User can choose to view all, active or completed tasks.
 */
public class FoodFragment extends Fragment implements FoodContract.View {

    @Bind(R.id.tv_food_time)
    TextView mTvFoodTime;
    @Bind(R.id.et_food_amount)
    EditText mEtFoodAmount;
    @Bind(R.id.btn_add_foodtime)
    Button mBtnAddFoodtime;
    @Bind(R.id.btn_save)
    Button mBtnSave;
    private FoodContract.Presenter mPresenter;


    public FoodFragment() {
        // Requires empty public constructor
    }

    public static FoodFragment newInstance() {
        return new FoodFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull FoodContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);
        ButterKnife.bind(this, root);

//
//        // Set up tasks view
//        ListView listView = (ListView) root.findViewById(R.id.tasks_list);
//        listView.setAdapter(mListAdapter);
//        mFilteringLabelView = (TextView) root.findViewById(R.id.filteringLabel);
//        mFoodsView = (LinearLayout) root.findViewById(R.id.tasksLL);
//
//        // Set up  no tasks view
//        mNoFoodsView = root.findViewById(R.id.noFoods);
//        mNoFoodIcon = (ImageView) root.findViewById(R.id.noFoodsIcon);
//        mNoFoodMainView = (TextView) root.findViewById(R.id.noFoodsMain);
//        mNoFoodAddView = (TextView) root.findViewById(R.id.noFoodsAdd);
//        mNoFoodAddView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAddFood();
//            }
//        });
//
//        // Set up floating action button
//        FloatingActionButton fab =
//                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_task);
//
//        fab.setImageResource(R.drawable.ic_add);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.addNewFood();
//            }
//        });
//
//        // Set up progress indicator
//        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
//                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
//        swipeRefreshLayout.setColorSchemeColors(
//                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
//                ContextCompat.getColor(getActivity(), R.color.colorAccent),
//                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
//        );
//        // Set the scrolling view in the custom SwipeRefreshLayout.
//        swipeRefreshLayout.setScrollUpChild(listView);
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.loadFoods(false);
//            }
//        });
//
//        setHasOptionsMenu(true);
//
//        return root;

        return root;
    }


    @Override
    public void showFoodTime() {
        new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time;
                DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
                time = df.format(new Date());
                mTvFoodTime.setText(time + " " + hourOfDay + "时" + minute + "分");
            }
        }, DateTimeUtils.getHour(), DateTimeUtils.getMinute(), true).show();
    }

    @Override
    public void showAddOneFoodRecord() {
        Toast.makeText(getActivity(),"添加记录成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFoodRecords() {

    }

    @Override
    public void showFoodType() {

    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_add_foodtime, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_foodtime://增加进食时间
                showFoodTime();
                break;
            case R.id.btn_save://保存
                Food food = new Food("2016-08-16", 90, "1");
                mPresenter.addNewFoodRecord(food);
                break;
        }
    }
}
