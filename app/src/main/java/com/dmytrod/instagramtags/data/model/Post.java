
package com.dmytrod.instagramtags.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("id")
    private String mId;
    @SerializedName("images")
    private Images mImages;
    @SerializedName("tags")
    private List<String> mTags;
    @SerializedName("user")
    private User mUser;


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Images getImages() {
        return mImages;
    }

    public void setImages(Images images) {
        mImages = images;
    }

    public List<String> getTags() {
        return mTags;
    }

    public void setTags(List<String> tags) {
        mTags = tags;
    }


    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

}
