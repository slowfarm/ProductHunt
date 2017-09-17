package eva.android.com.producthunt;

import eva.android.com.producthunt.interfaces.http.ProductHuntApi;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application extends android.app.Application {
    private static ProductHuntApi api;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        OkHttpClient httpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.producthunt.com/v1/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ProductHuntApi.class);
    }

    public static ProductHuntApi getApi() {
        return api;
    }
}
