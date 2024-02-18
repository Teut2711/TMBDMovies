package org.teut2711.network;


import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public interface NetworkClient {
    Response get(String url, Map<String, String> queryParams) throws IOException;
}
