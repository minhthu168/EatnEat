package fpt.aptech.eatneatapp.service;

import java.util.List;

import fpt.aptech.eatneatapp.entities.Viewemployee;
import fpt.aptech.eatneatapp.entities.Vieworderdetail;
import fpt.aptech.eatneatapp.entities.Vieworders;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {
    @GET("/api/orderList/{empid}")
    Call<List<Vieworders>> findAllOrders(@Path("empid")String empid);

    @GET("/api/orderDetailList/{orderid}")
    Call<List<Vieworderdetail>> findAllOrderDetail(@Path("orderid")int orderid);

    @POST("/api/saveOrder")
    @FormUrlEncoded
    Call<Integer> saveOrder(@Field("empid")String empid, @Field("quantity")int quantity, @Field("total")int total);

    @POST("/api/saveOrderDetail")
    @FormUrlEncoded
    Call<Integer> saveOrderDetail(@Field("orderid")int orderid,@Field("foodid")int foodid, @Field("quantity")int quantity);
}
