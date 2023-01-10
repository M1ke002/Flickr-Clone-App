package com.example.flickrr.search_package;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickrr.R;
import com.flickr4java.flickr.groups.Group;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>{
    private Collection<Group> mListGroups;

    public GroupsAdapter(Collection<Group> mListGroups){
        this.mListGroups = mListGroups;
    }

    @NonNull
    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_groups, parent, false);
        return new GroupsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsViewHolder holder, int position) {
        List<Group> a = new ArrayList<>(mListGroups);
        Group groups = a.get(position);
        if(groups == null){
            return;
        }
        holder.bind(groups);
    }

    @Override
    public int getItemCount() {
        if(mListGroups != null){
            return mListGroups.size();
        }
        return 0;
    }

    public class GroupsViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView imgGroups;
        private TextView tvName;
        private TextView tvMembers;
        private TextView tvPhotos;

        public GroupsViewHolder(@NonNull View itemView){
            super(itemView);
            imgGroups = itemView.findViewById(R.id.img_groups);
            tvName = itemView.findViewById(R.id.tv_name_groups);
            tvMembers = itemView.findViewById(R.id.tv_members);
            tvPhotos = itemView.findViewById(R.id.tv_photos_groups);
        }
        public void bind(Group c){
            tvName.setText(c.getName());
            tvPhotos.setText(c.getPhotoCount() +" Photos ");
            tvMembers.setText(c.getMembers() + " Members");
            Log.e("photo",c.getSecureBuddyIconUrl());
            Picasso.get().load(c.getSecureBuddyIconUrl()).into(imgGroups);
        }
    }
    public void addData(Collection<Group> res) {
        mListGroups.addAll(res);
        notifyDataSetChanged();
    }

}
