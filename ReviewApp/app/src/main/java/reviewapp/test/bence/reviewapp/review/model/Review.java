package reviewapp.test.bence.reviewapp.review.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import reviewapp.test.bence.reviewapp.travelers.TravelerType;

public class Review {

    @SerializedName("review_id")
    private long id;

    @SerializedName("rating")
    private float rating;

    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;

    @SerializedName("author")
    private String author;

    @SerializedName("foreignLanguage")
    private boolean foreignLanguage;

    @SerializedName("date")
    private String formattedDate;

    @SerializedName("languageCode")
    private String languageCode;

    @SerializedName("traveler_type")
    private TravelerType travelerType;

    @SerializedName("reviewerName")
    private String name;

    @SerializedName("reviewerCountry")
    private String country;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getMessage() {
        return message;
    }

    public TravelerType getTravelerType() {
        return travelerType != null ? travelerType : TravelerType.UNKNOWN;
    }

    public String getFormattedDate() {
        return formattedDate;
    }
}
