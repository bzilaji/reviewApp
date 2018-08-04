package reviewapp.test.bence.reviewapp.review.model;

import com.google.gson.annotations.SerializedName;

import reviewapp.test.bence.reviewapp.travelers.TravelerType;

public class MyReview {

    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;

    @SerializedName("traveler_type")
    private TravelerType travelerType;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTravelerType(TravelerType travelerType) {
        this.travelerType = travelerType;
    }
}
