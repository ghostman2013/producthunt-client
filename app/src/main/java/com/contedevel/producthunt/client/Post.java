package com.contedevel.producthunt.client;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    @JsonProperty("id")
    private int mId;

    @JsonProperty("name")
    private String mName;

    @JsonProperty("tagline")
    private String mDescription;


    @JsonProperty("discussion_url")
    private String mDiscussionUrl;

    @JsonProperty("votes_count")
    private int mVotes;

    @JsonIgnore
    private String mThumbnailUrl;

    @JsonIgnore
    private String mScreenshotUrl;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDiscussionUrl() {
        return mDiscussionUrl;
    }

    public int getVotes() {
        return mVotes;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    @Nullable
    public String getScreenshotUrl() {
        return mScreenshotUrl;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("thumbnail")
    private void unpackThumbnail(Map<String,Object> thumbnail) {
        mThumbnailUrl = (String)thumbnail.get("image_url");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("screenshot_url")
    private void unpackScreenshot(Map<String,Object> screenshot) {
        Set<String> keySet = screenshot.keySet();

        if (keySet.size() > 0) {
            String key = Collections.max(keySet);
            mScreenshotUrl = (String) screenshot.get(key);
        }
    }
}
