package com.sum.corp.imagessubredditviewer.view.list;

import android.content.Context;

import com.sum.corp.imagessubredditviewer.data.model.Children;
import com.sum.corp.imagessubredditviewer.data.network.HttpImpl;
import com.sum.corp.imagessubredditviewer.data.network.response.RedditResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

public class ImageListPresenter implements ImageListContract.Presenter {

    private Context context;
    private Disposable disposable;
    ImageListContract.View view;

    public ImageListPresenter(Context context, ImageListContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getPostData() {
        disposable = HttpImpl.getInstance(context).getPostData().subscribeWith(new DisposableObserver<Response<RedditResponse>>() {
            @Override
            public void onNext(Response<RedditResponse> redditResponse) {
                if (redditResponse.code() == 200) {
                    view.setImages(redditResponse.body().getData().getChildren());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void onDispose() {

        if (disposable != null) {
            disposable.dispose();
        }
    }

}
