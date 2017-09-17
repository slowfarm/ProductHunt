package eva.android.com.producthunt.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eva.android.com.producthunt.R;
import eva.android.com.producthunt.adapters.PostsAdapter;
import eva.android.com.producthunt.dialog.CategoryDialog;
import eva.android.com.producthunt.helpers.RetrofitHelper;
import eva.android.com.producthunt.helpers.StorageHelper;
import eva.android.com.producthunt.interfaces.DataBaseChangeListener;
import eva.android.com.producthunt.interfaces.OnItemClickListener;
import eva.android.com.producthunt.interfaces.OnPostsReceived;
import eva.android.com.producthunt.models.Post;
import eva.android.com.producthunt.models.Topic;

public class MainActivity extends AppCompatActivity implements OnItemClickListener,
        DataBaseChangeListener, SwipeRefreshLayout.OnRefreshListener, OnPostsReceived {

    private PostsAdapter adapter;
    private CategoryDialog categoryDialog;
    private TextView categoryName;
    private int currentId = -1;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StorageHelper.getInstance().setDataBaseChangeListener(this);
        RetrofitHelper.getInstance().setOnPostsReceived(this);
        categoryName = findViewById(R.id.category_name);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        initAdapter();
        RetrofitHelper.getInstance().getPosts(getResources().getString(R.string.access_token));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initAdapter() {
        Topic topic = StorageHelper.getInstance().getTechCategory();
        List<Post> postList = new ArrayList<>();
        if (topic != null) {
            postList = StorageHelper.getInstance().getPostListById(topic.getId());
            categoryName.setText(topic.getName());
            findViewById(R.id.category).setOnClickListener(view -> showSelectCategoryDialog());
            currentId = topic.getId();
        }
        adapter = new PostsAdapter(postList);
    }

    private void showSelectCategoryDialog() {
        categoryDialog = new CategoryDialog(this, this);
        categoryDialog.show();
    }

    @Override
    public void onItemClick(int id, String name) {
        currentId = id;
        adapter.addAll(StorageHelper.getInstance().getPostListById(id));
        categoryName.setText(name);
        categoryDialog.dismiss();
    }

    @Override
    public void onRefresh() {
        RetrofitHelper.getInstance().getPosts(getResources().getString(R.string.access_token));
    }

    @Override
    public void onChange() {
        Topic topic;
        if (currentId == -1)
            topic = StorageHelper.getInstance().getFirstCategory();
        else
            topic = StorageHelper.getInstance().getCategoryById(currentId);
        adapter.addAll(StorageHelper.getInstance().getPostListById(topic.getId()));
        categoryName.setText(topic.getName());
        findViewById(R.id.category).setOnClickListener(view -> showSelectCategoryDialog());
    }

    @Override
    public void onResponse(List<Post> posts) {
        StorageHelper.getInstance().setResponse(posts);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(MainActivity.this, getResources().getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StorageHelper.getInstance().closeRealm();
    }
}

