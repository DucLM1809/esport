package com.example.esport.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.esport.R;
import com.example.esport.model.Product;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AdminProductAdapter extends BaseAdapter {
    private AdminActivity context;
    private int layout;
    private List<Product> productList;

    public AdminProductAdapter(AdminActivity context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    private class ViewHolder {
        ImageView productImg;
        TextView productName, productDescription, productPrice, productTag;
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
            holder.productImg = (ImageView) view.findViewById(R.id.admin_product_image);
            holder.productName = (TextView) view.findViewById(R.id.admin_product_name);
            holder.productDescription = (TextView) view.findViewById(R.id.admin_product_description);
            holder.productPrice = (TextView) view.findViewById(R.id.admin_product_price);
            holder.productTag = (TextView) view.findViewById(R.id.admin_product_tag);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText("$" + product.getPrice() + " -");
        holder.productTag.setText(product.getTag());
        Glide.with(context).load(product.getImage()).into(holder.productImg);
        // holder.productImg.setImageBitmap(LoadImageFromWebOperations(product.getImage()));

        return view;
    }

    private static Bitmap LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}
