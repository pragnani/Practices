package com.pbsi.training.resource;

import org.springframework.hateoas.ResourceSupport;

import com.pbsi.training.entity.BlogEntry;

/**
 * Created by Chris on 6/27/14.
 */
public class BlogEntryResource extends ResourceSupport {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogEntry toBlogEntry() {
        BlogEntry entry = new BlogEntry();
        entry.setTitle(title);
        return entry;
    }
}
