package vn.iotstar.listviewpractice;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    // Đường dẫn cơ sở (Base URL) là: http://app.iotstar.vn:8081/
    // Endpoint cụ thể là: appfoods/categories.php

    @GET("appfoods/categories.php")
    Call<List<Category>> getCategories();
}