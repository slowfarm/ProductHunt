package eva.android.com.producthunt.helpers;

import java.util.List;

import eva.android.com.producthunt.interfaces.DataBaseChangeListener;
import eva.android.com.producthunt.models.Post;
import eva.android.com.producthunt.models.Topic;
import io.realm.Realm;
import io.realm.Sort;

public class StorageHelper {

    private static volatile StorageHelper instance;
    private Realm mRealm;
    private DataBaseChangeListener mDataBaseChangeListener;

    public void setDataBaseChangeListener(DataBaseChangeListener dataBaseChangeListener) {
        mDataBaseChangeListener = dataBaseChangeListener;
        if (dataBaseChangeListener != null) {
            mRealm = Realm.getDefaultInstance();
            mRealm.addChangeListener(realm1 -> mDataBaseChangeListener.onChange());
        } else {
            mRealm.close();
        }
    }

    public static StorageHelper getInstance() {
        if (instance == null)
            instance = new StorageHelper();
        return instance;
    }

    public void setResponse(List<Post> posts) {
        mRealm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(posts));
    }

    public List<Post> getPostListById(int id) {
        return mRealm.copyFromRealm(mRealm.where(Post.class).equalTo("topics.id", id).findAll());
    }

    public Topic getTechCategory() {
        return mRealm.where(Topic.class).equalTo("name", "Tech").findFirst();
    }

    public Topic getFirstCategory() {
        return mRealm.where(Topic.class).findAllSorted("id", Sort.DESCENDING).first();
    }

    public Topic getCategoryById(int currentId) {
        return mRealm.where(Topic.class).equalTo("id", currentId).findFirst();
    }

    public Post getPostById(int id) {
        return mRealm.where(Post.class).equalTo("id", id).findFirst();
    }

    public List<Topic> getCategoryList() {
        return mRealm.where(Topic.class).findAllSorted("id", Sort.DESCENDING);
    }

    public void closeRealm() {
        mRealm.close();
    }
}
