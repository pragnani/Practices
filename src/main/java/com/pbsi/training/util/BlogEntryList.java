package com.pbsi.training.util;

import java.util.ArrayList;
import java.util.List;

import com.pbsi.training.entity.BlogEntry;

/**
 * Created by Chris on 6/28/14.
 */
public class BlogEntryList {
    private List<BlogEntry> entries = new ArrayList<BlogEntry>();
    private Long blogId;

    public List<BlogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntry> entries) {
        this.entries = entries;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
