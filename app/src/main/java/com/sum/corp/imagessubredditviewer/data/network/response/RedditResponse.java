package com.sum.corp.imagessubredditviewer.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sum.corp.imagessubredditviewer.data.model.PostData;

public class RedditResponse {
    @SerializedName("data")
    @Expose
    private PostData data;

    public PostData getData() {
        return data;
    }

    public void setData(PostData data) {
        this.data = data;
    }
}
