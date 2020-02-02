package com.example.fakefblivestream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public  class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.CommnetViewHolder> {
    private Context context;
    private List<CommentPojo> commentPojoList;
    private int[] userImage;
    View view;


    public CommentRVAdapter(Context context, List<CommentPojo> commentPojoList) {
        this.context = context;
        this.commentPojoList = commentPojoList;
        this.userImage = userImage;
    }

    @NonNull
    @Override
    public CommnetViewHolder onCreateViewHolder(@NonNull  ViewGroup parent,  int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.comment_row, parent, false);
        return new CommnetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CommnetViewHolder holder,  int position) {

        holder.userName.setText(commentPojoList.get(position).getUserName());
        holder.userComment.setText(commentPojoList.get(position).getUserComment());
        holder.userImage.setImageResource(commentPojoList.get(position).getImagePath());


    }


    @Override
    public int getItemCount() {
        return commentPojoList.size();
    }


    public class CommnetViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userComment;
        ImageView userImage,likebtn;

        public CommnetViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userNameID);
            userComment = itemView.findViewById(R.id.userComment);
            userImage = itemView.findViewById(R.id.userImageID);
            likebtn = itemView.findViewById(R.id.likebtnID);
        }


    }
    public void UpdateCommnet(CommentPojo comment)
    {
        commentPojoList.add(comment);
        notifyDataSetChanged();
    }



}
