package com.contedevel.producthunt.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Topic {

    @JsonProperty("id")
    private int mId;

    @JsonProperty("name")
    private String mName;

    @JsonProperty("slug")
    private String mSlug;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getSlug() {
        return mSlug;
    }

    @Override
    public String toString() {
        return mName;
    }
}
