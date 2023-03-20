package com.example.esport.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.esport.R;
import com.example.esport.model.OrderItem;

import java.util.List;

public class CheckoutProductAdapter extends BaseAdapter {
    private CheckoutActivity context;
    private int layout;
    private List<OrderItem> orderItemList;

    public CheckoutProductAdapter(CheckoutActivity context, int layout, List<OrderItem> orderItemList) {
        this.context = context;
        this.layout = layout;
        this.orderItemList = orderItemList;
    }

    private class ViewHolder{
        TextView tvCheckOutOrderName,tvCheckOutOrderPrice,tvCHeckOutOrderQuantity;
        ImageView ivCheckOutOrderImg;
        Button btnsub,btnadd;
    }

    @Override
    public int getCount() {
        return orderItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            holder = new CheckoutProductAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.ivCheckOutOrderImg = (ImageView) view.findViewById(R.id.ivCheckoutProductImg);
            holder.tvCheckOutOrderName = (TextView) view.findViewById(R.id.tvCheckoutProductName);
            holder.tvCheckOutOrderPrice = (TextView) view.findViewById(R.id.tvCheckoutProductCost);
            holder.tvCHeckOutOrderQuantity = (TextView) view.findViewById(R.id.tvCheckoutIemQuantity);
            holder.btnsub = (Button) view.findViewById(R.id.buttonSub);
            holder.btnadd = (Button) view.findViewById(R.id.buttonAdd);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        OrderItem orderItem = orderItemList.get(position);
        Log.d("TAG", "4444: ");
        Glide.with(context).load(orderItem.getImage()).into(holder.ivCheckOutOrderImg);
        holder.tvCheckOutOrderName.setText(orderItem.getName());
        holder.tvCheckOutOrderPrice.setText("$"+orderItem.getPrice()+"");
        holder.tvCHeckOutOrderQuantity.setText(orderItem.getCartQuantity()+"");

        holder.btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderItem.getCartQuantity()>0){
                    orderItem.setQuantity(orderItem.getCartQuantity()-1);
                    holder.tvCHeckOutOrderQuantity.setText(orderItem.getCartQuantity()+"");
                    context.setTextData();
                }
            }
        });

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderItem.getCartQuantity()<orderItem.getCartQuantity()){
                    orderItem.setQuantity(orderItem.getCartQuantity()+1);
                    holder.tvCHeckOutOrderQuantity.setText(orderItem.getCartQuantity()+"");
                    context.setTextData();
                }
            }
        });

        return view;
    }
}
