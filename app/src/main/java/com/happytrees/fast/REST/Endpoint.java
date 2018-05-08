package com.happytrees.fast.REST;

import com.happytrees.fast.Model.MyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Endpoint {
    @GET("/3/search/movie?api_key=7feab044341d650481d056f7b6fe4441&language=en-US")
    Call<MyResponse>getMyResults(@Query("query")String query);

}


//https://api.themoviedb.org/3/search/movie?api_key=7feab044341d650481d056f7b6fe4441&query=alien

