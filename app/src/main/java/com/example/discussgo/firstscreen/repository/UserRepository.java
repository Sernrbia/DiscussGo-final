package com.example.discussgo.firstscreen.repository;

import com.example.discussgo.addon.SharedPreferencesWrapper;
import com.example.discussgo.firstscreen.network.AccessToken;
import com.example.discussgo.firstscreen.network.ApiClient;
import com.example.discussgo.firstscreen.network.ApiInterface;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserRepository {


    private static UserRepository instance;
    private static final String ACCESS_TOKEN = "AccessToken";
    ApiInterface apiInterface;
    private SharedPreferencesWrapper wrapper;


    public static UserRepository getInstance(SharedPreferencesWrapper wrapper) {
        if (instance == null) {
            instance = new UserRepository(wrapper);
        }
        return instance;
    }

    private UserRepository(SharedPreferencesWrapper wrapper) {


        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        this.wrapper = wrapper;

    }


    public Observable<AccessToken> signIn(final String username, final String password) {
        return apiInterface.signIn(username, password, "androidId", "password")
                .delay(2, TimeUnit.SECONDS)
                .map(accessToken -> {
                    wrapper.putAccessToken(accessToken.getAccessToken());
                    return accessToken;
                });
    }



}
