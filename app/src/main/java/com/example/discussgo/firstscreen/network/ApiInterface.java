package com.example.discussgo.firstscreen.network;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("token")
    Observable<AccessToken> signIn(@Field("username") String username, @Field("password") String password, @Field("client_id") String clientId, @Field("grant_type") String grantType);

}