package com.sirisakj.technews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sirisakj.technews.rest.ApiManager;
import com.sirisakj.technews.rest.newsapi.model.response.ArticleListResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApiTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        // Test Api
        Response<ArticleListResponse> res = ApiManager.getInstance().newsApi.getArticlesBySource("techcrunch").execute();
        assertTrue("Call newsapi techcrunch is successul", res.isSuccessful());
        assertTrue("techcrunch-body is status ok", res.body().getStatus().equals("ok"));
        assertNotNull("techcrunch-body articles not null", res.body().getArticles());
    }
}
