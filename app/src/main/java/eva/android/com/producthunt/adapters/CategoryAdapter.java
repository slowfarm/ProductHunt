package eva.android.com.producthunt.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eva.android.com.producthunt.R;
import eva.android.com.producthunt.interfaces.OnItemClickListener;
import eva.android.com.producthunt.models.Topic;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Topic> mCategoryList;
    private OnItemClickListener onItemClickListener;

    public CategoryAdapter(List<Topic> categoryList) {
        mCategoryList = categoryList;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mCategoryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(view -> {
                Topic category = mCategoryList.get(getAdapterPosition());
                onItemClickListener.onItemClick(category.getId(), category.getName());
            });
        }
    }
}
