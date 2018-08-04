package reviewapp.test.bence.reviewapp.review;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import reviewapp.test.bence.reviewapp.databinding.ReviewItemBinding;
import reviewapp.test.bence.reviewapp.review.model.Review;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    private ReviewItemBinding binding;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.findBinding(itemView);
    }

    public void setModel(Review review) {
        if (binding != null) {
            binding.setReview(review);
        }
    }



}
