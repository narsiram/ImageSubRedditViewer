package com.sum.corp.imagessubredditviewer.data.network;

import android.content.Context;

import com.sum.corp.imagessubredditviewer.data.network.response.RedditResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class HttpImpl {

    private static volatile HttpImpl sInstance;
    private static NetworkInterface sApiClient;

    static Context context;

    public static HttpImpl getInstance(Context cont) {
        context = cont;

        if (sInstance == null) {
            synchronized (HttpImpl.class) {
                sInstance = new HttpImpl();
            }
        }
        return sInstance;
    }

    private NetworkInterface getApiClient() {
        if (sApiClient == null) {
            synchronized (this) {
                NetworkConnectivity.context = context;
                sApiClient = NetworkConnectivity.createRetrofit2RxJavaService(NetworkInterface.class);
            }
        }
        return sApiClient;
    }

    public Observable<Response<RedditResponse>> getPostData() {
        return getApiClient().getPostData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}