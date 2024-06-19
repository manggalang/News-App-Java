package org.galangcode.newsapp;

import org.galangcode.newsapp.models.NewsHeadline;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadline> list, String message);
    void onError(String message);
}
