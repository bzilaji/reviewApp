package reviewapp.test.bence.reviewapp.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import reviewapp.test.bence.reviewapp.CreateReviewActivity;
import reviewapp.test.bence.reviewapp.ReviewActivity;
import reviewapp.test.bence.reviewapp.review.service.ReviewService;

@Component(modules = MainModule.class)
@Singleton
public interface MainComponent {

    Context providesContext();

    ReviewService providesReviewService();

    void inject(ReviewActivity activity);

    void inject(CreateReviewActivity createReviewActivity);
}
