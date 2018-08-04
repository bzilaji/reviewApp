package reviewapp.test.bence.reviewapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import javax.inject.Inject;

import reviewapp.test.bence.reviewapp.dagger.InjectHelper;
import reviewapp.test.bence.reviewapp.databinding.ActivityReviewBinding;
import reviewapp.test.bence.reviewapp.review.ReviewAdapter;
import reviewapp.test.bence.reviewapp.review.download.ReviewDownloadHelper;
import reviewapp.test.bence.reviewapp.review.service.ReviewService;


public class ReviewActivity extends AppCompatActivity {

    public static final String CITY_ID = "berlin-l17";
    public static final String TOUR_ID = "tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776";
    ActivityReviewBinding binding;
    ReviewDownloadHelper downloadHelper;

    @Inject
    ReviewService reviewService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelper.getComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ReviewAdapter reviewAdapter = new ReviewAdapter();
        binding.recyclerView.setAdapter(reviewAdapter);
        downloadHelper = new ReviewDownloadHelper(reviewAdapter, binding.recyclerView, binding.emptyView, reviewService, CITY_ID, TOUR_ID);
        //consider starting with result, if data refresh needed
        binding.fab.setOnClickListener(v -> CreateReviewActivity.startActivity(this, CITY_ID, TOUR_ID));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        downloadHelper.onDestroy();
    }
}
