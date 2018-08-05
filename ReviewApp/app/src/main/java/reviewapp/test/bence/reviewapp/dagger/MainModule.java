package reviewapp.test.bence.reviewapp.dagger;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import reviewapp.test.bence.reviewapp.review.model.NewReview;
import reviewapp.test.bence.reviewapp.review.model.ReviewResponse;
import reviewapp.test.bence.reviewapp.review.model.ReviewWrapper;
import reviewapp.test.bence.reviewapp.review.service.ReviewService;
import reviewapp.test.bence.reviewapp.util.NetworkUtil;

@Module
public class MainModule {

    public static final String MOCK_SERVICE = "MOCK_SERVICE";
    private Application application;
    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    public MainModule(Application application) {
        this.application = application;
    }

    @Provides
    public Context providesContext() {
        return application;
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://www.getyourguide.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public Gson providesGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
    }

    @Provides
    @Singleton
    public ReviewService providesReviewService(Retrofit retrofit) {
        return retrofit.create(ReviewService.class);
    }

    @Provides
    @Singleton
    @Named(MOCK_SERVICE)
    public ReviewService providesMockReviewService() {
        return new ReviewService() {
            @Override
            public Single<ReviewWrapper> getReviews(String city, String tourId, int pageSize, int page) {
                return null;
            }

            @Override
            public Single<ReviewResponse> sendReviews(String city, String tourId, NewReview review) {
                ReviewResponse item = new ReviewResponse();
                item.setId(new Random().nextLong());
                item.setStatus(200);
                return Single.just(item).delay(200, TimeUnit.MILLISECONDS);
            }
        };
    }


    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(chain -> {
            //setting up dummy user agent to avoid 403
            Request.Builder request = chain.request().newBuilder().header("User-Agent", "My user agent");
            return chain.proceed(request.build());
        });
        httpClientBuilder.addInterceptor(chain -> {
            Request.Builder request = chain.request().newBuilder();
            if (!NetworkUtil.isNetworkAvailable(application)) {
                request.cacheControl(new CacheControl.Builder().onlyIfCached().build());
                int maxStale = 60 * 60 * 24 * 28; // 4 weeks old cache when in offline mode
                request.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);

            }
            return chain.proceed(request.build());
        });
        httpClientBuilder.addNetworkInterceptor(chain -> {
            Response proceed = chain.proceed(chain.request());
            // hacking Cache-Control so caching start working.
            // This is dangerous, would be great if server would send back header.
            CacheControl.Builder builder = new CacheControl.Builder().maxStale(2, TimeUnit.MINUTES);
            return proceed.newBuilder().header("Cache-Control", builder.build().toString()).removeHeader("pragma").build();
        });
        httpClientBuilder.cache(new Cache(application.getCacheDir(), CACHE_SIZE));
        return httpClientBuilder.build();
    }
}
