package com.contedevel.producthunt.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ServiceApi {

    private static final String HOST = "https://api.producthunt.com/v1/";
    private static final String HEAD_AUTH = "Authorization";
    private static final String HEAD_BEARER = "Bearer " + Config.ACCESS_TOKEN;

    private OkHttpClient mClient = new OkHttpClient();
    private ObjectMapper mObjMapper = new ObjectMapper();

    public Topic[] getTopics() {
        final String url = HOST + "topics?search[trending]=true";
        Request request = new Request.Builder()
                .url(url)
                .header(HEAD_AUTH, HEAD_BEARER)
                .build();
        try {
            Response responses = mClient.newCall(request).execute();
            ResponseBody body = responses.body();

            if (body != null) {
                return mObjMapper.readValue(body.string(), TopicsWrapper.class).topics;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Topic[0];
    }

    public Post[] getPosts(int topicId) {
        final String url = HOST + "posts/all?search[topic]=" + topicId;
        Request request = new Request.Builder()
                .url(url)
                .header(HEAD_AUTH, HEAD_BEARER)
                .build();
        try {
            Response responses = mClient.newCall(request).execute();
            ResponseBody body = responses.body();

            if (body != null) {
                return mObjMapper.readValue(body.string(), PostsWrapper.class).posts;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Post[0];
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class TopicsWrapper {
        Topic[] topics;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PostsWrapper {
        Post[] posts;
    }
}
