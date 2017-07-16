package com.sirisakj.technews.fragment;

import android.os.Bundle;
import android.util.Log;

import com.sirisakj.lib.fragment.BaseFragment;
import com.sirisakj.technews.R;
import com.sirisakj.technews.component.bind.FeedViewBind;
import com.sirisakj.technews.databinding.FragmentFeedsBinding;
import com.sirisakj.technews.fragment.bind.FeedsFragmentBind;
import com.sirisakj.technews.rest.ApiManager;
import com.sirisakj.technews.rest.newsapi.model.data.ArticleModel;
import com.sirisakj.technews.rest.newsapi.model.response.ArticleListResponse;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedsFragment extends BaseFragment<FragmentFeedsBinding, FeedsFragment.FeedsFragmentArg> implements Callback<ArticleListResponse> {

    public FeedsFragment() {
        super();
    }

    public static FeedsFragment newInstance() {
        FeedsFragment fragment = new FeedsFragment();
        FeedsFragmentArg args = new FeedsFragmentArg();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_feeds;
    }

    @Override
    protected void initInstances(FragmentFeedsBinding pBinding, Bundle savedInstanceState) {

        pBinding.setBind(new FeedsFragmentBind());

        ApiManager.getInstance().newsApi.getArticlesBySource("techcrunch").enqueue(this);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {
        Log.d("#####","Header = "+call.request().url());
        if(response.isSuccessful()){
            ArticleListResponse body = response.body();
            for (final ArticleModel article : body.getArticles()) {
                getBinding().list.addItem(new FeedViewBind() {
                    {
                        imageUrl.set(article.getUrlToImage());
                        content.set(article.getDescription());
                        author.set(article.getAuthor());
                        title.set(article.getTitle());
                        link.set(article.getUrl());
                    }
                });
            }

            getBinding().list.notifyDataSetChanged();

        }

    }

    @Override
    public void onFailure(Call<ArticleListResponse> call, Throwable t) {
        Log.d("#####","fail = "+t.getMessage());
    }

    public static class FeedsFragmentArg implements Serializable {

    }
}
