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

    public void getPosts(String accessToken) {
        Application.getApi().getPosts(accessToken).enqueue(new Callback<HttpResponse>() {
            @Override
            public void onResponse(Call<HttpResponse> call, Response<HttpResponse> response) {
                onPostsReceived.onResponse(response.body().getPosts());
            }

            @Override
            public void onFailure(Call<HttpResponse> call, Throwable t) {
                onPostsReceived.onFailure(t);
            }
        });
    }

    public void setOnPostsReceived(OnPostsReceived onPostsReceived) {
        this.onPostsReceived = onPostsReceived;
    }
}
