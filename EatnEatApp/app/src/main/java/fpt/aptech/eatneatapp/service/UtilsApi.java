package fpt.aptech.eatneatapp.service;

public class UtilsApi {
    public static final String url="http://172.16.0.215:9999/";
    public static FoodService getFoodService(){
        return RetrofitClient.getClient(url).create(FoodService.class);
    }
    public static LoginService checkLogin(){
        return RetrofitClient.getClient(url).create(LoginService.class);
    }
    public static OrderService checkOut(){
        return RetrofitClient.getClient(url).create(OrderService.class);
    }


}
