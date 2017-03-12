package followers.githubusersfollowers.data.injection.utils;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubRepoHelper {
    public static final String BASE_URL = "https://api.github.com/";

    private static Retrofit getRetrofitInstance() {


        Retrofit retroClient = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retroClient;
    }

    public static GithubRepoService getGithubRepoService() {

        return getRetrofitInstance().create(GithubRepoService.class);
    }

}
