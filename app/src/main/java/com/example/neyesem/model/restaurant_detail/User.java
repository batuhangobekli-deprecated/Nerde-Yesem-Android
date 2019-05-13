package com.example.neyesem.model.restaurant_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("zomato_handle")
    @Expose
    private String zomatoHandle;
    @SerializedName("foodie_level")
    @Expose
    private String foodieLevel;
    @SerializedName("foodie_level_num")
    @Expose
    private String foodieLevelNum;
    @SerializedName("foodie_color")
    @Expose
    private String foodieColor;
    @SerializedName("profile_url")
    @Expose
    private String profileUrl;
    @SerializedName("profile_deeplink")
    @Expose
    private String profileDeeplink;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;

    public String getName() {
        return name;
    }

    public String getZomatoHandle() {
        return zomatoHandle;
    }

    public String getFoodieLevel() {
        return foodieLevel;
    }

    public String getFoodieLevelNum() {
        return foodieLevelNum;
    }

    public String getFoodieColor() {
        return foodieColor;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getProfileDeeplink() {
        return profileDeeplink;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
