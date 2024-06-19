package org.galangcode.newsapp;

import org.galangcode.newsapp.models.NewsHeadline;

public interface SelectListener {
    void OnNewsClicked(NewsHeadline headline);
}
