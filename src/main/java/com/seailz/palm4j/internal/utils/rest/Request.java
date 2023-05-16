package com.seailz.palm4j.internal.utils.rest;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Request {

    private final Path path;
    private final JSONObject body;

    public Request(Path path, JSONObject body) {
        this.path = path;
        this.body = body;
    }

    public Path getPath() {
        return path;
    }

    public JSONObject getBody() {
        return body;
    }

    public GoogleApiResponse send() {
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create the request URI
        URI uri;
        try {
            uri = new URI(path.formattedUrl());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new GoogleApiResponse("{}", true, -1);
        }

        // Build the request
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json");

        if (Objects.equals(path.method, "GET"))
            requestBuilder.GET();
        else if (Objects.equals(path.method, "POST"))
            requestBuilder.POST(HttpRequest.BodyPublishers.ofString(body.toString(), StandardCharsets.UTF_8));
        else
            requestBuilder.method(path.method, HttpRequest.BodyPublishers.ofString(body.toString(), StandardCharsets.UTF_8));

        // Send the request
        try {
            HttpResponse<String> httpResponse = httpClient.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            String responseBody = httpResponse.body();

            if (statusCode == 200) {
                // Successful response
                return new GoogleApiResponse(Objects.equals(responseBody, "") ? "{}" : responseBody, false, statusCode);
            } else {
                // Error response
                return new GoogleApiResponse(Objects.equals(responseBody, "") ? "{}" : responseBody, true, statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new GoogleApiResponse("{}", true, -1);
        }
    }

    public record Path(String url, String[] queryArgs, String method) {
        public String formattedUrl() {
            return String.format(url, (Object[]) queryArgs);
        }
    }

    public record GoogleApiResponse(String body, boolean error, int errorCode) {}
}
