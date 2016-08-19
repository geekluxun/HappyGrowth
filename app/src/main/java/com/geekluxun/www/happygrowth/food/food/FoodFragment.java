package com.geekluxun.www.happygrowth.food.food;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.geekluxun.www.happygrowth.R;
import com.geekluxun.www.happygrowth.food.fooddetail.FoodDetailActivity;
import com.geekluxun.www.happygrowth.util.DateTimeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class FoodFragment extends Fragment implements FoodContract.View {

    @Bind(R.id.tv_food_time)
    TextView mTvFoodTime;
    @Bind(R.id.et_food_amount)
    EditText mEtFoodAmount;
    @Bind(R.id.btn_add_foodtime)
    Button mBtnAddFoodtime;
    @Bind(R.id.btn_save)
    Button mBtnSave;
    @Bind(R.id.btn_food_record)
    Button mBtnFoodRecord;
    @Bind(R.id.rb_milk)
    RadioButton mRbMilk;
    @Bind(R.id.rb_water)
    RadioButton mRbWater;
    @Bind(R.id.rg_food_type)
    RadioGroup mRgFoodType;

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
        return root;
    }


    @Override
    public void showFoodTime() {
        new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time;
                DateFormat df = new SimpleDateFormat(DateTimeUtils.DateFormat1);
                time = df.format(new Date());
                String hourStr = String.format("%02d",hourOfDay);
                String minuteStr = String.format("%02d",minute);
                mTvFoodTime.setText(time + " " + hourStr + "时" + minuteStr + "分");
            }
        }, DateTimeUtils.getHour(), DateTimeUtils.getMinute(), true).show();
    }

    @Override
    public void showAddOneFoodRecord() {
        Toast.makeText(getActivity(), "添加记录成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFoodRecords() {
        startActivity(new Intent(getActivity(), FoodDetailActivity.class));
    }

    @Override
    public void showFoodType(String type) {
        switch (type){
            case "牛奶":
                mRbMilk.setChecked(true);
                break;
            case "水":
                mRbWater.setChecked(true);
                break;
            default:
                mRbMilk.setChecked(true);
                break;
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public String getFoodType() {

        String type = null;

        if (mRgFoodType.getCheckedRadioButtonId() == mRbMilk.getId()){
            type =  "牛奶";
        }else if (mRgFoodType.getCheckedRadioButtonId() == mRbWater.getId()){
            type =  "水";
        }

        return type;
    }

    @Override
    public void showAddFoodRecordSuccess() {
        Toast.makeText(getActivity(),"保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFoodAmountError() {
        Toast.makeText(getActivity(),"请输入正确食量", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFoodDateError() {
        Toast.makeText(getActivity(),"请输入正确日期", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({  R.id.btn_add_foodtime,
                R.id.btn_save,
                R.id.btn_food_record,
                R.id.rb_milk,
                R.id.rb_water
                })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_foodtime://增加进食时间
                showFoodTime();
                break;
            case R.id.btn_save://保存
                mPresenter.addNewFoodRecord(mTvFoodTime.getText().toString(),mEtFoodAmount.getText().toString(),getFoodType());
                break;
            case R.id.btn_food_record:
                mPresenter.openFoodRecords();
                break;
            default:
                break;
        }
    }

}
