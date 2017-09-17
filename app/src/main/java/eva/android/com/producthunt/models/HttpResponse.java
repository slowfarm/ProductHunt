package eva.android.com.producthunt.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class HttpResponse extends RealmObject {

    @SerializedName("posts")
    @Expose
    private RealmList<Post> posts = null;

    public RealmList<Post> getPosts() {
        return posts;
    }

    public void setPosts(RealmList<Post> posts) {
        this.posts = posts;
    }
}
