package com.legacy.analysis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp3.OkHttpClient;
import com.squareup.okhttp3.Request;
import com.squareup.okhttp3.Response;

import java.io.IOException;

/**
 * LLM transformer for legacy code.
 */
public class LLMTransformer {
    private OkHttpClient client;
    private Gson gson;

    public LLMTransformer() {
        this.client = new OkHttpClient();
        this.gson = new GsonBuilder().create();
    }

    public String transformCode(String code) throws Exception {
        // Send request to LLM API to transform code
        Request request = new Request.Builder()
                .url("https://llm-api.com/transform")
                .post(com.squareup.okhttp3.RequestBody.create(code))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Get transformed code from response
            String transformedCode = response.body().string();
            return transformedCode;
        }
    }
}
