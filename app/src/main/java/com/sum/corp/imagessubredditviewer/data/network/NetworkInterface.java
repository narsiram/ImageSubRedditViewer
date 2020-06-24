package com.sum.corp.imagessubredditviewer.data.network;

import com.sum.corp.imagessubredditviewer.data.network.response.RedditResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface NetworkInterface {

    @GET("/r/images/hot.json")
    Observable<Response<RedditResponse>> getPostData();
}
