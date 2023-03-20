package com.example.esport.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.esport.model.orderRequest.OrderRequest;
import com.example.esport.model.orderResponse.OrderResponse;
import com.example.esport.service.OrderRepository;
import com.example.esport.service.OrderService;
import com.example.esport.view.OrderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class OrderPresenter {
    private OrderView orderView;
    private OrderService orderService;

    public OrderPresenter(OrderView orderView){
        this.orderView = orderView;
        if(this.orderService == null){
            this.orderService = OrderRepository.getOrderService();
        }
    }

    public void getAllOrders(){
        try{
            Call<OrderResponse[]> call = orderService.getAllOrders();
            call.enqueue(new Callback<OrderResponse[]>() {
                @Override
                public void onResponse(Call<OrderResponse[]> call, Response<OrderResponse[]> response) {
                    OrderResponse[] orders = response.body();

                    if(orders == null){
                        return;
                    }

                    List<OrderResponse> orderResponseList = new ArrayList<>();
                    for(OrderResponse orderResponse : orders){
                        orderResponseList.add(orderResponse);
                    }

                    orderView.orderReady(orderResponseList);

                }

                @Override
                public void onFailure(Call<OrderResponse[]> call, Throwable t) {

                }
            });
        } catch (Exception e){

        }
    }

    public boolean createOrder(OrderRequest orderRequest){
        try{
            Call<OrderResponse> call = orderService.createOrder(orderRequest);
            call.enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                    if(response.body() != null){
                        Log.d("TAG", "Success: ");
                    }
                }

                @Override
                public void onFailure(Call<OrderResponse> call, Throwable t) {

                }
            });
            return true;

        }catch (Exception e){
            return false;
        }
    }

}
