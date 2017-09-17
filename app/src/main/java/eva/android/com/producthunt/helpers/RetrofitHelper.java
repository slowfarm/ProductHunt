package eva.android.com.producthunt.helpers;


import eva.android.com.producthunt.Application;
import eva.android.com.producthunt.interfaces.OnPostsReceived;
import eva.android.com.producthunt.models.HttpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitHelper {
    private static RetrofitHelper instance;
    private OnPostsReceived onPostsReceived;

    public static RetrofitHelper getInstance() {
        if (instance == null)
            instance = new RetrofitHelper();
        return instance;
    }

    public void setOnPostsReceived(OnPostsReceived onPostsReceived) {
        this.onPostsReceived = onPostsReceived;
    }
}
