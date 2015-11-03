package com.pbsi.training.service;

import com.pbsi.training.entity.Account;
import com.pbsi.training.entity.Blog;

/**
 * Created by Chris on 6/28/14.
 */
public interface AccountService {
    public Account findAccount(Long id);
    public Account createAccount(Account data);
    public Blog createBlog(Long accountId, Blog data);
}
