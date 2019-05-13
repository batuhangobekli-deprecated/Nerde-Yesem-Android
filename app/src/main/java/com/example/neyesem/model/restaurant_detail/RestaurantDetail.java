package com.example.neyesem.model.restaurant_detail;

import com.example.neyesem.model.UserRating;
import com.example.neyesem.model.nearby_restaurants.Location;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantDetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("average_cost_for_two")
    @Expose
    private String averageCostForTwo;
    @SerializedName("price_range")
    @Expose
    private String priceRange;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("featured_image")
    @Expose
    private String featuredImage;
    @SerializedName("photos_url")
    @Expose
    private String photosUrl;
    @SerializedName("menu_url")
    @Expose
    private String menuUrl;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("user_rating")
    @Expose
    private UserRating userRating;
    @SerializedName("has_online_delivery")
    @Expose
    private String hasOnlineDelivery;
    @SerializedName("is_delivering_now")
    @Expose
    private String isDeliveringNow;
    @SerializedName("has_table_booking")
    @Expose
    private String hasTableBooking;
    @SerializedName("deeplink")
    @Expose
    private String deeplink;
    @SerializedName("cuisines")
    @Expose
    private String cuisines;
    @SerializedName("all_reviews_count")
    @Expose
    private String allReviewsCount;
    @SerializedName("photo_count")
    @Expose
    private String photoCount;
    @SerializedName("phone_numbers")
    @Expose
    private String phoneNumbers;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("all_reviews")
    @Expose
    private List<AllReview> allReviews = null;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Location getLocation() {
        return location;
    }

    public String getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public String getThumb() {
        return thumb;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public String getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public String getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public String getHasTableBooking() {
        return hasTableBooking;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public String getCuisines() {
        return cuisines;
    }

    public String getAllReviewsCount() {
        return allReviewsCount;
    }

    public String getPhotoCount() {
        return photoCount;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public List<AllReview> getAllReviews() {
        return allReviews;
    }
}
