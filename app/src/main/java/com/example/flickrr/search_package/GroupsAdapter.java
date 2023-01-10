package com.example.flickrr.search_package;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickrr.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>{
    private List<Groups> mListGroups;

    public GroupsAdapter(List<Groups> mListGroups){
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
        Groups groups = mListGroups.get(position);
        if(groups == null){
            return;
        }
        holder.imgGroups.setImageResource(groups.getImage());
        holder.tvName.setText(groups.getName());
        holder.tvMembers.setText(groups.getMembers());
        holder.tvPhotos.setText(groups.getPhotos());
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
    }
}
