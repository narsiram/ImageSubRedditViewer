package com.sum.corp.imagessubredditviewer.view.list;

import com.sum.corp.imagessubredditviewer.data.model.Children;

import java.util.List;

public interface ImageListContract {

    interface View{
        void setImages(List<Children> imageList);
    }

    interface Presenter{
        void getPostData();
        void onDispose();
    }
}
