package com.tayyeb.mychatapp.Fragments;

import com.tayyeb.mychatapp.Notifications.MyResponse;
import com.tayyeb.mychatapp.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
@Headers(
        {
                "Content-Type:application/json",
                // add your own key from firebase project
                "Authorization:key=Add your key from firebase project " }
)
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
