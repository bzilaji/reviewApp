package reviewapp.test.bence.reviewapp.review;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import reviewapp.test.bence.reviewapp.databinding.ProgressItemBinding;
import reviewapp.test.bence.reviewapp.databinding.ReviewItemBinding;
import reviewapp.test.bence.reviewapp.review.model.Review;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int VIEW_TYPE_REVIEW = 1;
    private static int VIEW_TYPE_PROGRESS_INDICATOR = 2;

    private final List<Review> reviews;
    private boolean showProgressIndicator;

    public ReviewAdapter() {
        reviews = new ArrayList<>();
    }

    public void addMoreData(List<Review> reviews, boolean hasMore) {
        addItems(reviews, hasMore);
    }

    private void addItems(List<Review> reviews, boolean hasMore) {
        showProgressIndicator = hasMore;
        int start = this.reviews.size();
        if (reviews != null) {
            this.reviews.addAll(reviews);
            notifyItemRangeInserted(start, reviews.size());
        }
    }

    public void clear() {
        reviews.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_REVIEW) {
            return new ReviewViewHolder(ReviewItemBinding.inflate(inflater, parent, false).getRoot());
        } else {
            return new IndicatorViewHolder(ProgressItemBinding.inflate(inflater, parent, false).getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ReviewViewHolder) {
            ((ReviewViewHolder) holder).setModel(reviews.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return showProgressIndicator ? reviews.size() + 1 : reviews.size();
    }

    @Override
    public int getItemViewType(int position) {
        return showProgressIndicator && position == reviews.size() ? VIEW_TYPE_PROGRESS_INDICATOR : VIEW_TYPE_REVIEW;
    }

    public void setShowProgressIndicator(boolean show) {
        if (show != showProgressIndicator) {
            showProgressIndicator = !showProgressIndicator;
            //TODO: use notifyItemInserted and notifyItemRemoved
            notifyDataSetChanged();
        }
    }

}
