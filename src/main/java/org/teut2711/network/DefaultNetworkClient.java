package org.teut2711.network;
import okhttp3.*;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class DefaultNetworkClient implements NetworkClient {
    private final OkHttpClient httpClient;
    private final String bearerToken;

    public DefaultNetworkClient(String bearerToken) {
        this.httpClient = new OkHttpClient();
        this.bearerToken = bearerToken;
    }

    @Override
    public Response get(@NotNull  String url, @NotNull Map<String, String> queryParams) throws IOException {

        Headers headers = new Headers.Builder()
                    .add("Authorization", String.format("Bearer %s", bearerToken))
                    .build();


        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                        urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                    }


        Request.Builder requestBuilder = new Request.Builder()
                    .url(urlBuilder.build())
                    .headers(headers);

        Request request = requestBuilder.build();

        return httpClient.newCall(request).execute();

    }
}
