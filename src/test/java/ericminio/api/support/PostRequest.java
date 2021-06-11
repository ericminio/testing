package ericminio.api.support;

import ericminio.http.support.Stringify;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PostRequest {

    public static HttpResponse post(String url, String data) throws Exception {
        return post(url, new HashMap<>(), data.getBytes());
    }

    public static HttpResponse post(String url, byte[] data) throws Exception {
        return post(url, new HashMap<>(), data);
    }

    public static HttpResponse post(String url, Map<String, String> headers, byte[] data) throws Exception {
        return post(url, headers, data, "application/json");
    }

    public static HttpResponse post(String url, Map<String, String> headers, byte[] data, String contentType) throws Exception {
        HttpURLConnection request = (HttpURLConnection) new URL( url ).openConnection();
        request.setDoOutput(true);
        request.setRequestMethod("POST");
        request.setRequestProperty( "Content-Type", contentType);
        request.setRequestProperty( "Content-Length", Integer.toString(data.length));
        for (String header: headers.keySet()) {
            request.setRequestProperty( header, headers.get(header));
        }
        request.getOutputStream().write(data);

        HttpResponse response = new HttpResponse();
        response.setStatusCode(request.getResponseCode());
        response.setContentType(request.getContentType());
        if (request.getResponseCode() < 400) {
            response.setBody(new Stringify().inputStream(request.getInputStream()));
        } else {
            response.setBody(new Stringify().inputStream(request.getErrorStream()));
        }

        return response;
    }
}
