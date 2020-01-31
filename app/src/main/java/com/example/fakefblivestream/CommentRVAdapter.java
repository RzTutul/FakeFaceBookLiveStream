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

public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.CommnetViewHolder> {
    private Context context;
    private List<CommentPojo> commentPojoList;
    View view;


    public CommentRVAdapter(Context context, List<CommentPojo> commentPojoList) {
        this.context = context;
        this.commentPojoList = commentPojoList;
    }

    @NonNull
    @Override
    public CommnetViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.comment_row, parent, false);
        return new CommnetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommnetViewHolder holder, final int position) {

        holder.userName.setText(commentPojoList.get(position).getUserName());
        holder.userComment.setText(commentPojoList.get(position).getUserComment());


    }


    @Override
    public int getItemCount() {
        return commentPojoList.size();
    }


    public class CommnetViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userComment;
        ImageView userImage;

        public CommnetViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userNameID);
            userComment = itemView.findViewById(R.id.userComment);
            userImage = itemView.findViewById(R.id.userImageID);
        }
    }
}
