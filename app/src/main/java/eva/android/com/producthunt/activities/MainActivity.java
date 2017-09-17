package eva.android.com.producthunt.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eva.android.com.producthunt.R;
import eva.android.com.producthunt.adapters.PostsAdapter;
import eva.android.com.producthunt.dialog.CategoryDialog;
import eva.android.com.producthunt.interfaces.OnItemClickListener;
import eva.android.com.producthunt.models.Post;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

    private PostsAdapter adapter;
    private TextView categoryName;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CategoryDialog categoryDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryName = findViewById(R.id.category_name);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        List<Post> postList = new ArrayList<>();
        Post post = new Post();
        postList.add(post);
        postList.add(post);
        postList.add(post);
        postList.add(post);

        adapter = new PostsAdapter(postList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.category).setOnClickListener(view -> showSelectCategoryDialog());
    }

    private void showSelectCategoryDialog() {
        categoryDialog = new CategoryDialog(this, this);
        categoryDialog.show();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(int id, String name) {
        Log.d("TAG", "onItemClick: " + id + "  " + name);
        categoryDialog.dismiss();
    }
}
