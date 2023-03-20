package Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.esport.R;
import com.example.esport.model.OrderItem;
import com.example.esport.model.Product;
import com.example.esport.view.ViewCart;

import java.util.List;

public class ViewCartAdapter extends RecyclerView.Adapter<ViewCartAdapter.CheckoutViewHolder> {

    private ViewCart context;
    private int layout;
    private List<OrderItem> OrderItemList;

    public ViewCartAdapter(ViewCart context, int layout, List<OrderItem> orderItemList) {
        this.context = context;
        this.layout = layout;
        OrderItemList = orderItemList;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_row, parent, false);

        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        OrderItem item = OrderItemList.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("$"+item.getPrice());
        Glide.with(context).load(item.getImage()).into(holder.img);





    }

    @Override
    public int getItemCount() {
        return OrderItemList.size();
    }

    public  static class CheckoutViewHolder extends RecyclerView.ViewHolder{

        TextView name, price;
        ImageView img;

        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageViewCheckout);
            name = itemView.findViewById(R.id.textViewName);
            price = itemView.findViewById(R.id.textViewPrice);

        }
    }

}
