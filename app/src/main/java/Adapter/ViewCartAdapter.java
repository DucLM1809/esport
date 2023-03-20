package Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.esport.R;
import com.example.esport.model.OrderItem;
import com.example.esport.model.Product;
import com.example.esport.presenter.CartPresenter;
import com.example.esport.view.CartView;
import com.example.esport.view.ViewCart;

import java.util.List;

public class ViewCartAdapter extends RecyclerView.Adapter<ViewCartAdapter.ViewCartViewHolder> {

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
    public ViewCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_row, parent, false);

        return new ViewCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCartViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        OrderItem item = OrderItemList.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("$"+item.getPrice());
        Glide.with(context).load(item.getImage()).into(holder.img);
        holder.qty.setText(item.getCartQuantity()+ "");
        
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteCartItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return OrderItemList.size();
    }

    public  static class ViewCartViewHolder extends RecyclerView.ViewHolder implements CartView {
        TextView name, price, qty;
        ImageView img;
        Button btnRemove;


        public ViewCartViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageViewCheckout);
            name = itemView.findViewById(R.id.textViewName);
            price = itemView.findViewById(R.id.textViewPrice);
            btnRemove = itemView.findViewById(R.id.buttonRemove);
            qty = itemView.findViewById(R.id.tvCartIemQuantity);

            CartPresenter cartPresenter = new CartPresenter(this);

            
        }

        @Override
        public void CartReady(List<OrderItem> items) {

        }
    }

}
