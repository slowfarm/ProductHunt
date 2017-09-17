package eva.android.com.producthunt.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eva.android.com.producthunt.R;
import eva.android.com.producthunt.adapters.PostsAdapter;
import eva.android.com.producthunt.models.Post;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private PostsAdapter adapter;
    private TextView categoryName;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryName = findViewById(R.id.category_name);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        List<Post> postList = new ArrayList<>();

        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());

        adapter = new PostsAdapter(postList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {

    }
}
