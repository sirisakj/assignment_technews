package com.sirisakj.technews.rest.newsapi.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sirisakj.technews.rest.newsapi.model.response.header.HeaderModel;
import com.sirisakj.technews.rest.newsapi.model.data.ArticleModel;

import java.util.List;

/**
 * Created by icsme on 15-Jul-17.
 */

public class ArticleListResponse extends HeaderModel {
    @SerializedName("articles")
    @Expose
    private List<ArticleModel> articles = null;


    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }
}
