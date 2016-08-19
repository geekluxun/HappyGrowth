package com.geekluxun.www.happygrowth.food.fooddetail;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geekluxun.www.happygrowth.R;
import com.geekluxun.www.happygrowth.food.domain.model.Food;
import com.geekluxun.www.happygrowth.food.food.FoodAmountRange;
import com.geekluxun.www.happygrowth.util.DateTimeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodDetailFragment extends Fragment implements FoodDetailContract.View {

    @Bind(R.id.sp_food_amount)
    Spinner mSpFoodAmount;
    @Bind(R.id.sp_food_type)
    Spinner mSpFoodType;
    @Bind(R.id.lv_food_detail)
    ListView mLvFoodDetail;
    @Bind(R.id.tv_milk_amount)
    TextView mTvMilkAmount;
    @Bind(R.id.tv_water_amount)
    TextView mTvWaterAmount;
    @Bind(R.id.tv_food_time)
    TextView mTvFoodTime;

    private FoodDetailContract.Presenter mFoodDetailPresenter;
    private FoodAmountRange mFoodAmountRange = FoodAmountRange.ALL;


    public FoodDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mFoodDetailPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static FoodDetailFragment newInstance() {
        return new FoodDetailFragment();
    }

    @Override
    public void showFoodDetail(final List<Food> foods) {
        mLvFoodDetail.setAdapter(new FoodDetailAdapter(getActivity(), foods));
        mLvFoodDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mFoodDetailPresenter.deleteFood(foods.get(position));// 删除选择的item
            }
        });
    }

    @Override
    public void showMilkAmountToday(int amount) {
        mTvMilkAmount.setText(amount + " mL");
    }

    @Override
    public void showWaterAmonutToday(int amount) {
        mTvWaterAmount.setText(amount + " mL");
    }

    @Override
    public void showSearchCondition(String date, String amount, String type) {

        mTvFoodTime.setText(date);

        mSpFoodAmount.setSelection(0, false);

        mSpFoodAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //final  String[] item = getResources().getStringArray(R.array.food_amount_range);

                for(FoodAmountRange range : FoodAmountRange.values()){
                    if (range.ordinal() == position){
                        mFoodAmountRange = range;
                        break;
                    }
                }
                mFoodDetailPresenter.setCurSearchCondition( mTvFoodTime.getText().toString(),
                                                            mFoodAmountRange,
                                                            mSpFoodType.getSelectedItem().toString());
                mFoodDetailPresenter.getFoodDetail();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mSpFoodType.setSelection(0,false);
        mSpFoodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mFoodDetailPresenter.setCurSearchCondition( mTvFoodTime.getText().toString(),
                                                            mFoodAmountRange,
                                                            mSpFoodType.getSelectedItem().toString());
                mFoodDetailPresenter.getFoodDetail();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void setPresenter(FoodDetailContract.Presenter presenter) {
        mFoodDetailPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.tv_food_time)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_food_time:
                mFoodDetailPresenter.selectFoodDate();
                break;
            default:
                break;
        }
    }

    @Override
    public void showDatePickerDialog() {
        myDatePickerDialog dialog = new myDatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Date date1 = new Date(year - 1900, monthOfYear, dayOfMonth);
                DateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.DateFormat1);
                mTvFoodTime.setText(dateFormat.format(date1));

                mFoodDetailPresenter.setCurSearchCondition( mTvFoodTime.getText().toString(),
                                                            mFoodAmountRange,
                                                            mSpFoodType.getSelectedItem().toString());
                mFoodDetailPresenter.getFoodDetail();

            }
        }, DateTimeUtils.getYear(), DateTimeUtils.getMonth(), DateTimeUtils.getDay());
        dialog.show();
    }

    @Override
    public void showNoRecord() {
        Toast.makeText(getActivity(),"暂无记录",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteRecordSuccess() {
        Toast.makeText(getActivity(),"删除记录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteRecordDialog(final FoodDetailContract.FoodDelDigCallback callback) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
        builder.setMessage("确认要删除此记录吗？");
        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onClickOk();
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onClickCancel();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public class  myDatePickerDialog extends  DatePickerDialog{

        public myDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
            super(context, callBack, year, monthOfYear, dayOfMonth);
        }

        @Override
        protected void onStop() {
        }
    }
}
