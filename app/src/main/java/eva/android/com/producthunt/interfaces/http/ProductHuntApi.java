package eva.android.com.producthunt.interfaces.http;

import eva.android.com.producthunt.models.HttpResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductHuntApi {

    @POST("/v1/posts")
    Call<HttpResponse> getPosts(@Query("access_token") String accessToken);
}
