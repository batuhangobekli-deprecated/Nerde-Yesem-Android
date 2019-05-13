package com.example.neyesem.model.restaurant_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllReview {
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("review_text")
    @Expose
    private String reviewText;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rating_color")
    @Expose
    private String ratingColor;
    @SerializedName("review_time_friendly")
    @Expose
    private String reviewTimeFriendly;
    @SerializedName("rating_text")
    @Expose
    private String ratingText;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("comments_count")
    @Expose
    private String commentsCount;

    public String getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getId() {
        return id;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public String getReviewTimeFriendly() {
        return reviewTimeFriendly;
    }

    public String getRatingText() {
        return ratingText;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLikes() {
        return likes;
    }

    public User_ getUser() {
        return user;
    }

    public String getCommentsCount() {
        return commentsCount;
    }
}
