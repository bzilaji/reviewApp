package reviewapp.test.bence.reviewapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import reviewapp.test.bence.reviewapp.R;
import reviewapp.test.bence.reviewapp.dagger.InjectHelper;
import reviewapp.test.bence.reviewapp.dagger.MainModule;
import reviewapp.test.bence.reviewapp.databinding.ContentCreateReviewBinding;
import reviewapp.test.bence.reviewapp.review.model.NewReview;
import reviewapp.test.bence.reviewapp.review.service.ReviewService;
import reviewapp.test.bence.reviewapp.travelers.TravelerType;

public class CreateReviewActivity extends AppCompatActivity {

    private static final String KEY_CITY_ID = "city_id";
    private static final String KEY_TOUR_ID = "tour_id";

    private ContentCreateReviewBinding viewDataBinding;
    private String tourId;
    private String cityId;

    @Inject
    @Named(MainModule.MOCK_SERVICE)
    ReviewService reviewService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tourId = getIntent().getStringExtra(KEY_TOUR_ID);
        cityId = getIntent().getStringExtra(KEY_CITY_ID);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.content_create_review);
        InjectHelper.getComponent().inject(this);
        viewDataBinding.submitButton.setOnClickListener(v -> submit());
        setupSpinner();
    }

    public static void startActivity(Activity activity, String cityId, String tourId) {
        Intent intent = new Intent(activity, CreateReviewActivity.class);
        intent.putExtra(KEY_CITY_ID, cityId);
        intent.putExtra(KEY_TOUR_ID, tourId);
        activity.startActivity(intent);
    }

    private void setupSpinner() {
        List<TravelerType> typeList = new ArrayList<>(3);
        typeList.add(TravelerType.COUPLE);
        typeList.add(TravelerType.FAMILY_OLD);
        typeList.add(TravelerType.SOLO);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                typeList);
        viewDataBinding.travelType.setAdapter(arrayAdapter);
    }

    private void submit() {
        NewReview review = new NewReview();
        review.setMessage(viewDataBinding.reviewInput.getText().toString());
        review.setTitle(viewDataBinding.reviewInput.getText().toString());
        review.setTravelerType((TravelerType) viewDataBinding.travelType.getSelectedItem());
        reviewService.sendReviews(cityId, tourId, review).subscribe(reviewResponse -> {
            if (reviewResponse.getStatus() == 200) {
                finish();
            }
            else
            {
                //TODO show error to user
            }
        });
    }


}
