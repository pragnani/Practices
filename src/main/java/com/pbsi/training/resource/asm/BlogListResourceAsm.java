package com.pbsi.training.resource.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.pbsi.training.controller.BlogController;
import com.pbsi.training.resource.BlogListResource;
import com.pbsi.training.util.BlogList;

/**
 * Created by Chris on 7/1/14.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm()
    {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource res = new BlogListResource();
        res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return res;
    }
}
