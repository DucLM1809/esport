package com.example.esport.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.esport.R;
import com.example.esport.model.OrderItem;
import com.example.esport.model.orderResponse.OrderItemResponse;
import com.example.esport.model.orderResponse.OrderResponse;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends BaseAdapter {

    private ProfileActivity context;
    private int layout;
    private ArrayList<OrderResponse> orderResponseArrayList;

    public ProfileAdapter(ProfileActivity context, int layout, ArrayList<OrderResponse> orderResponseArrayList) {
        this.context = context;
        this.layout = layout;
        this.orderResponseArrayList = orderResponseArrayList;
    }

    private class ViewHolder{
        TextView tvname, tvaddress, tvprice;
    }

    @Override
    public int getCount() {
        return orderResponseArrayList.size();
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
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvname = (TextView) view.findViewById(R.id.tvProfileOrderItemName);
            holder.tvaddress = (TextView) view.findViewById(R.id.tvProfileOrderItemAddress);
            holder.tvprice = (TextView) view.findViewById(R.id.tvProfileOrderItemPrice);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        OrderResponse orderResponse = orderResponseArrayList.get(position);
        long totalPrice = 0;
        ArrayList<OrderItemResponse> orderItemList = orderResponse.getOrderItems();
        for (OrderItemResponse orderItem: orderItemList) {
            totalPrice = orderItem.getPrice() * orderItem.getQuantity();
        }
        holder.tvname.setText(orderResponse.getOrderItems().get(0).getProduct().getName()+" and "+(orderResponse.getOrderItems().size()-1)+" others");
        holder.tvaddress.setText("Shipping to: "+orderResponse.getCity());
        holder.tvprice.setText("$"+totalPrice);
        return view;
    }
}
