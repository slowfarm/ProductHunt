package eva.android.com.producthunt.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import eva.android.com.producthunt.R;
import eva.android.com.producthunt.adapters.CategoryAdapter;
import eva.android.com.producthunt.helpers.StorageHelper;
import eva.android.com.producthunt.interfaces.OnItemClickListener;

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
        CategoryAdapter adapter = new CategoryAdapter(StorageHelper.getInstance().getCategoryList());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);
    }
}