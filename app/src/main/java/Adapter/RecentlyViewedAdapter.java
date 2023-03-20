package Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.esport.R;
import com.example.esport.model.Product;
import com.example.esport.view.MainActivity;
import com.example.esport.view.ProductDetails;
import com.example.esport.view.ViewCart;


import java.util.List;



public class RecentlyViewedAdapter extends RecyclerView.Adapter<RecentlyViewedAdapter.RecentlyViewedViewHolder> {

    private MainActivity context;
    private int layout;
    private List<Product> ProductList;

    public RecentlyViewedAdapter(MainActivity context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        ProductList = productList;
    }

    @NonNull
    @Override
    public RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_viewd_items, parent, false);

        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Product product = ProductList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText("$"+product.getPrice());
        Glide.with(context).load(product.getImage()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(context, ProductDetails.class);
                i.putExtra("product", product);


                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return ProductList.size();
    }

    public  static class RecentlyViewedViewHolder extends RecyclerView.ViewHolder{

        TextView name, price, qty, unit;
        ImageView img;

        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.imageViewRecently);

        }
    }

}
