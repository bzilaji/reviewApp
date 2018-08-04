package reviewapp.test.bence.reviewapp.review.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewWrapper {

    @SerializedName("status")
    private boolean status;

    @SerializedName("total_reviews_comments")
    private int totalReviews;

    @SerializedName("data")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public int getTotalReviews() {
        return totalReviews;
    }
}
