package com.juranoaa.restapisample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_main)
public class AAMainActivity extends Activity {

    private int requestCount = 0;

    @ViewById
    TextView textView;

    @RestService
    AARestProtocol aaRestProtocol;

    @Click
    void btnRequestToServerClicked() {
        requestToServer();
    }

    @Background
    void requestToServer() {
        requestCount++;
        Message message = aaRestProtocol.echoProtocol(new Message("Taehee", "Hello?" + requestCount));
        Log.d("TAG", "server echo: " + message.getData());
        setTextView(message.getData());
    }

    @UiThread
    void setTextView(String param) {
        textView.setText("server echo: " + param);
    }
}
