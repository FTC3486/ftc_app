package net.jacobmason.GroupMe;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Taken from http://loopj.com/android-async-http/
 */
public class RestClient {
    private static final String base_api_url = "https://api.groupme.com/v3/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(get_absolute_url(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(get_absolute_url(url), params, responseHandler);
    }

    private static String get_absolute_url(String relative_url) {
        return base_api_url + relative_url;
    }
}
