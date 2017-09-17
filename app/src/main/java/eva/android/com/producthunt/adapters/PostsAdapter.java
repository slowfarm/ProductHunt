package eva.android.com.producthunt.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eva.android.com.producthunt.R;
import eva.android.com.producthunt.activities.PostDetailsActivity;
import eva.android.com.producthunt.models.Post;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Post> mPostList;

    public PostsAdapter(List<Post> postList) {
        mPostList = postList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mPostList.get(position).getName());
        holder.description.setText(mPostList.get(position).getTagline());
        holder.voteCount.setText(String.valueOf(mPostList.get(position).getVotesCount()));

//        Picasso.with(holder.itemView.getContext())
//                .load(mPostList.get(position).getThumbnail().getImageUrl())
//                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final TextView voteCount;
        private final ImageView thumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            voteCount = itemView.findViewById(R.id.vote_count);
            thumbnail = itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), PostDetailsActivity.class);
                intent.putExtra("id", mPostList.get(getAdapterPosition()).getId());
                view.getContext().startActivity(intent);
            });
        }
    }
}
