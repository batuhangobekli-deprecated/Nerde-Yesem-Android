package com.example.neyesem.model.nearby_restaurants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {
   @SerializedName("entity_type")
    @Expose
    private String entityType;
    @SerializedName("entity_id")
    @Expose
    private Integer entityId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("country_name")
    @Expose
    private String countryName;

    public String getEntityType() {
     return entityType;
    }

    public Integer getEntityId() {
     return entityId;
    }

    public String getTitle() {
     return title;
    }

    public String getLatitude() {
     return latitude;
    }

    public String getLongitude() {
     return longitude;
    }

    public Integer getCityId() {
     return cityId;
    }

    public String getCityName() {
     return cityName;
    }

    public Integer getCountryId() {
     return countryId;
    }

    public String getCountryName() {
     return countryName;
    }
   }
