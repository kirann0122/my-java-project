package com.legacy.analysis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp3.MediaType;
import com.squareup.okhttp3.OkHttpClient;
import com.squareup.okhttp3.Request;
import com.squareup.okhttp3.RequestBody;
import com.squareup.okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LLMTransformer {
    private static final String LLM_API_URL = "https://api.example.com/llm-transform";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Gson GSON = new GsonBuilder().create();
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    public String transformCode(String code) throws IOException {
        RequestBody body = RequestBody.create(JSON, code);
        Request request = new Request.Builder()
                .url(LLM_API_URL)
                .post(body)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
