package com.sirisakj.technews.rest.newsapi.route;

import com.sirisakj.technews.config.Constant;
import com.sirisakj.technews.rest.newsapi.model.response.ArticleListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by icsme on 15-Jul-17.
 */
public interface NewsApiRoute {
        @GET("articles?apiKey=" + Constant.NEWS_API_KEY)
        Call<ArticleListResponse> getArticlesBySource(@Query("source") String source);

}
