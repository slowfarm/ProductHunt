package eva.android.com.producthunt.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import eva.android.com.producthunt.R;
import eva.android.com.producthunt.adapters.CategoryAdapter;
import eva.android.com.producthunt.interfaces.OnItemClickListener;
import eva.android.com.producthunt.models.Post;
import eva.android.com.producthunt.models.Topic;

public class CategoryDialog extends Dialog{

    private Activity activity;
    private OnItemClickListener onItemClickListener;

    public CategoryDialog(Activity activity, OnItemClickListener onItemClickListener) {
        super(activity);
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.category_dialog);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        List<Topic> categoryList = new ArrayList<>();

        Topic topic = new Topic();
        topic.setName("Name");
        topic.setId(3);
        categoryList.add(topic);
        categoryList.add(topic);
        categoryList.add(topic);
        categoryList.add(topic);
        categoryList.add(topic);
        CategoryAdapter adapter = new CategoryAdapter(categoryList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);
    }
}