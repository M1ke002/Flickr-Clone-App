package com.example.flickrr;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flickr4java.flickr.photos.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagePostAdapter extends RecyclerView.Adapter<ImagePostAdapter.ViewHolder> {

    private List<PostData> postData;

    public ImagePostAdapter(List<PostData> postData) {
        this.postData = postData;
    }

    public void setPhotos(List<PostData> postData) {
        this.postData = postData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostData data = postData.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return postData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView usernameTextView;
        TextView postTitle;
        TextView recommendationTextView;
        ImageButton moreButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.post_img);
            postTitle = itemView.findViewById(R.id.post_title);
            usernameTextView = itemView.findViewById(R.id.feed_username);
            recommendationTextView = itemView.findViewById(R.id.feed_recommend);
            moreButton = itemView.findViewById(R.id.feed_more);
        }

        public void bind(PostData postData) {
            // Set the data for the views using the Photo object

            try {
                Picasso.get().load(postData.url).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Set the username and recommendation text using the data from the Photo object
            usernameTextView.setText(postData.username);
            postTitle.setText(postData.title);
            recommendationTextView.setText("Recommended");
        }
    }

    public void addData(List<PostData> newData) {
        postData.addAll(newData);
        notifyDataSetChanged();
    }
}
