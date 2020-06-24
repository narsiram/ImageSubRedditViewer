package com.sum.corp.imagessubredditviewer.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Children {

    @SerializedName("data")
    @Expose
    private ChildrenData childrenData;

    public ChildrenData getChildrenData() {
        return childrenData;
    }

    public void setChildrenData(ChildrenData childrenData) {
        this.childrenData = childrenData;
    }
}
