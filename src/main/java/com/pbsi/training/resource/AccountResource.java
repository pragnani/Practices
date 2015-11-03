package com.pbsi.training.resource;

import org.springframework.hateoas.ResourceSupport;

import com.pbsi.training.entity.Account;

/**
 * Created by Chris on 6/28/14.
 */
public class AccountResource extends ResourceSupport {
    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        return account;
    }
}
