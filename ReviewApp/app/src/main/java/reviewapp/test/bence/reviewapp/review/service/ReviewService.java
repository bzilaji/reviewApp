package reviewapp.test.bence.reviewapp.review.service;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import reviewapp.test.bence.reviewapp.review.model.MyReview;
import reviewapp.test.bence.reviewapp.review.model.ReviewResponse;
import reviewapp.test.bence.reviewapp.review.model.ReviewWrapper;

public interface ReviewService {

    @GET("{city}/{tourId}/reviews.json?rating=0&type=&sortBy=date_of_review&direction=DESC")
    Single<ReviewWrapper> getReviews(@Path("city") String city, @Path("tourId") String tourId, @Query("count") int pageSize, @Query("page") int page);

    @POST("{city}/{tourId}/reviews")
    Single<ReviewResponse> sendReviews(@Path("city") String city, @Path("tourId") String tourId, @Body MyReview review);

}
