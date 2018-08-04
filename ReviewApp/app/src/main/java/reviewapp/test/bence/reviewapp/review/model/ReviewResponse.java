package reviewapp.test.bence.reviewapp.review.model;

import com.google.gson.annotations.SerializedName;

public class ReviewResponse {

    @SerializedName("statusCode")
    private int status;

    @SerializedName("errorMessage")
    private String errorMsg;

    @SerializedName("review_id")
    private long id;

    public long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
