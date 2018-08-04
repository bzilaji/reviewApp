package reviewapp.test.bence.reviewapp.review.download;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import reviewapp.test.bence.reviewapp.review.ReviewAdapter;
import reviewapp.test.bence.reviewapp.review.model.Review;
import reviewapp.test.bence.reviewapp.review.service.ReviewService;

public class ReviewDownloadHelper extends RecyclerView.OnScrollListener {

    private static final int PAGE_SIZE = 10;

    private final RecyclerView recyclerView;
    private final ReviewAdapter adapter;
    private View emptyView;
    private ReviewService reviewService;
    private final String city;
    private final String tourId;
    private int totalItems = 0;
    private int page = 0;
    private Disposable disposable;
    LinearLayoutManager layoutManager;
    private boolean downloading = false;
    private boolean hasMore = false;

    public ReviewDownloadHelper(ReviewAdapter adapter, RecyclerView recyclerView, View emptyView, ReviewService reviewService, String city, String tourId) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.emptyView = emptyView;
        this.reviewService = reviewService;
        this.city = city;
        this.tourId = tourId;
        loadItems();
        emptyView.setVisibility(View.GONE);
        layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(this);
    }

    private void loadItems() {
        disposable = reviewService.getReviews(city, tourId, PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewWrapper -> {
                    if (reviewWrapper != null) {
                        totalItems = reviewWrapper.getTotalReviews();
                        List<Review> reviews = reviewWrapper.getReviews();
                        page++;
                        hasMore = PAGE_SIZE * page < totalItems;
                        adapter.addMoreData(reviews, hasMore);
                        downloading = false;
                    } else {
                        handleError();
                    }
                }, error -> {
                    handleError();
                });
    }

    private void handleError() {
        adapter.setShowProgressIndicator(false);
        downloading = false;
        hasMore = false;
        if (page == 0) {
            emptyView.setVisibility(View.VISIBLE);
        }
    }


    public void onDestroy() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
        recyclerView.removeOnScrollListener(this);
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!downloading && hasMore) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                loadItems();
            }
        }
    }

}
