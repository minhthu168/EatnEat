package fpt.aptech.eatneatapp.service;

import java.util.List;

import fpt.aptech.eatneatapp.entities.Viewemployee;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginService {
    @GET("/api/employee")
    Call<List<Viewemployee>> findAll();

    @GET("/api/checkPhone/{phone}")
    Call<Viewemployee> findOne(@Path("phone")String phone);

    @POST("api/sendOTP")
    @FormUrlEncoded
    Call<String> sendOTP(@Field("phone") String phone);

}
