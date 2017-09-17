package eva.android.com.producthunt.interfaces;

import java.util.List;

import eva.android.com.producthunt.models.Post;

public interface OnPostsReceived {
    void onResponse(List<Post> posts);

    void onFailure(Throwable t);
}
