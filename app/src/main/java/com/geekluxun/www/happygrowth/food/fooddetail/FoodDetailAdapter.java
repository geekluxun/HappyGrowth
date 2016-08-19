package com.geekluxun.www.happygrowth.food.fooddetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geekluxun.www.happygrowth.R;
import com.geekluxun.www.happygrowth.food.domain.model.Food;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodDetailAdapter extends BaseAdapter {
    List<Food> mList;
    LayoutInflater mLayoutInflater;
    TextView tvTime;
    TextView tvAmount;
    TextView tvType;

    public FoodDetailAdapter(Context context, List<Food> list) {
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder viewholder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_food_detail, null);
            viewholder = new MyViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (MyViewHolder) convertView.getTag();
        }

        viewholder.mIvType.setText(mList.get(position).getType() + "");
        viewholder.mIvTime.setText(mList.get(position).getTime() + "");
        viewholder.mIvAmount.setText(mList.get(position).getAmount() + "mL");

        return convertView;
    }

    public static class MyViewHolder {
        @Bind(R.id.iv_time)
        TextView mIvTime;
        @Bind(R.id.iv_amount)
        TextView mIvAmount;
        @Bind(R.id.iv_type)
        TextView mIvType;

        MyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}