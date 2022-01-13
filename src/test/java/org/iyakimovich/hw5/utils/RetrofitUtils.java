package org.iyakimovich.hw5.utils;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@UtilityClass
public class RetrofitUtils {

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public Retrofit getRetrofit(){

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        return new Retrofit.Builder()
                .baseUrl(APITestUtils.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();

    }
}