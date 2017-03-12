package followers.githubusersfollowers.data.injection.utils;


import java.util.ArrayList;

import followers.githubusersfollowers.model.GithubUser;
import followers.githubusersfollowers.model.GithubUserFollowers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubRepoService {

    /**
     * Retunrs the list of Followers ofr a given gitbub repo user name
     *
     * @param username
     * @return
     */
    @GET("users/{username}/followers")
    Call<ArrayList<GithubUserFollowers>> getUserFollowers(@Path("username") String username);

    /**
     * Retunr the detail for a given github username
     *
     * @param username
     * @return
     */
    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);


}
