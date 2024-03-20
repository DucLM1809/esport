package com.example.esport.view;

import android.content.Context;
import android.content.Intent;
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
import com.example.esport.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeProductAdapter extends BaseAdapter {
    private HomeActivity context;
    private int layout;
    private List<Product> productList;

    public HomeProductAdapter(HomeActivity context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    private class ViewHolder{
        ImageView productImg;
        TextView tvproductName, tvproductPrice;
        Button btnAddToCard, btnBuyNow;
    }

    @Override
    public int getCount() {
        return productList.size();
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
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.productImg = (ImageView) view.findViewById(R.id.ivProductimg);
            holder.tvproductName = (TextView) view.findViewById(R.id.tvProductName);
            holder.tvproductPrice = (TextView) view.findViewById(R.id.tvProductPrice);
            holder.productImg = (ImageView) view.findViewById(R.id.ivProductimg);
            holder.btnAddToCard = (Button) view.findViewById(R.id.buttonAddToCard);
            holder.btnBuyNow = (Button) view.findViewById(R.id.buttonBuy);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Product product = productList.get(position);
        holder.tvproductName.setText(product.getName() + " (" + product.getQuantity() + " left)");
        Glide.with(context).load(product.getImage()).into(holder.productImg);
        holder.tvproductPrice.setText("$" + product.getPrice() + "");

        holder.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<OrderItem> OrderItemList = new ArrayList<>();

                OrderItemList.add(new OrderItem(product.getName(),product.getDescription(),product.getTag(), product.getImage(), product.getPrice(), product.getQuantity(), product.getId(), 1));

                Intent intent = new Intent(context, CheckoutActivity.class);
                intent.putExtra("OrderList",OrderItemList);
                context.startActivity(intent);
            }
        });


        holder.productImg.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     Intent intent = new Intent(context, ProductDetails.class);
                                                     context.startActivity(intent);
                                                 }
                                             });

        holder.btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductDetails.class);
                i.putExtra("product", product);
                context.startActivity(i);

            }
        });

        return view;
    }
}
