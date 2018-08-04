package reviewapp.test.bence.reviewapp;



import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import reviewapp.test.bence.reviewapp.application.MyApplication;
import reviewapp.test.bence.reviewapp.dagger.InjectHelper;
import reviewapp.test.bence.reviewapp.review.model.ReviewWrapper;
import reviewapp.test.bence.reviewapp.review.service.ReviewService;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {24}, constants = BuildConfig.class, application = MyApplication.class)
public class ReviewServiceTest {

    private ReviewService sut;

    @Before
    public void init() {
        sut = InjectHelper.getComponent().providesReviewService();
    }

    @Test
    public void testSimpleDownload() {
        ReviewWrapper reviewWrapper = sut.getReviews("berlin-l17", "tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776", 50, 0).blockingGet();
        Assert.assertEquals(50, reviewWrapper.getReviews().size());
    }

}
