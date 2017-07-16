package com.sirisakj.technews.rest;

import com.sirisakj.technews.config.Constant;
import com.sirisakj.technews.rest.newsapi.route.NewsApiRoute;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by icsme on 15-Jul-17.
 */

public class ApiManager {
    public final NewsApiRoute newsApi;
    private static ApiManager instance = null;

    private ApiManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.NEWS_API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApi = retrofit.create(NewsApiRoute.class);
    }

    public static ApiManager getInstance(){
        if(instance == null){
            instance = new ApiManager();
        }

        return instance;
    }
}
