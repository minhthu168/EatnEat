package fpt.aptech.eatneatapp.service;

import java.util.List;

import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Viewfood;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodService {
    @GET("/api/food")
    Call<List<Viewfood>> findAll();

    @GET("/api/menuSet")
    Call<List<Viewfood>> SetList();

    @GET("/api/menuOption")
    Call<List<Viewfood>> OptionList();

    @GET("/api/menutype")
    Call<List<Menutype>> findAllCat();
    @GET("/api/menuSet/{menuid}")
    Call<List<Viewfood>> searchByCat_SetList(@Path("menuid")Integer menuid);
    @GET("/api/menuOption/{menuid}")
    Call<List<Viewfood>> searchByCat_OptionList(@Path("menuid")Integer menuid);

    @GET("/api/menuSet/{type}")
    Call<List<Viewfood>> searchByCat_FoodList(@Path("menuid")String menuid);

    @GET("/api/findOneFood/{foodid}")
    Call<Viewfood> findOneFood(@Path("foodid")int foodid);

}
