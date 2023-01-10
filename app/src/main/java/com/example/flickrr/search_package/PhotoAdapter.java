package com.example.flickrr.search_package;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickrr.ImagePostAdapter;
import com.example.flickrr.PostData;
import com.example.flickrr.R;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.squareup.picasso.Picasso;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private PhotoList<Photo> photoList;

    public PhotoAdapter(PhotoList<Photo> photoList) {
        this.photoList = photoList;
    }
    public void setPhotos(PhotoList<Photo> photoList) {
        this.photoList = photoList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photos, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        Photo a = photoList.get(position);
        holder.bind(a);

    }


    @Override
    public int getItemCount() {
        return photoList.size();
    }



    public class PhotoHolder extends RecyclerView.ViewHolder{
        private ImageView photoview;

        public PhotoHolder(@NonNull View itemView){
            super(itemView);
            photoview = itemView.findViewById(R.id.imageView3);

        }

        public void bind(Photo photo) {
            try {
                Log.e("photo",photo.getLargeUrl());
                Picasso.get().load(photo.getLargeUrl()).into(photoview);

            } catch (Exception e) {
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                Log.d("picasso",errors.toString());
            }
        }
    }
    public void addData(PhotoList<Photo> newData) {
        photoList.addAll(newData);
        notifyDataSetChanged();
    }

}




