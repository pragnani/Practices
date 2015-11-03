package com.pbsi.training.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pbsi.training.entity.Account;
import com.pbsi.training.entity.Blog;
import com.pbsi.training.exceptions.BadRequestException;
import com.pbsi.training.exceptions.ConflictException;
import com.pbsi.training.resource.AccountResource;
import com.pbsi.training.resource.BlogResource;
import com.pbsi.training.resource.asm.AccountResourceAsm;
import com.pbsi.training.resource.asm.BlogResourceAsm;
import com.pbsi.training.rest.exceptions.AccountDoesNotExistException;
import com.pbsi.training.rest.exceptions.AccountExistsException;
import com.pbsi.training.service.AccountService;

/**
 * Created by Chris on 6/28/14.
 */
@Controller
@RequestMapping("/rest/accounts")
public class AccountController {
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
		try {
			Account createdAccount = accountService.createAccount(sentAccount.toAccount());
			AccountResource res = new AccountResourceAsm().toResource(createdAccount);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(res.getLink("self").getHref()));
			return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
		} catch (AccountExistsException exception) {
			throw new ConflictException(exception);
		}
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {
		Account account = accountService.findAccount(accountId);
		getClass().getGenericSuperclass();
		if (account != null) {
			AccountResource res = new AccountResourceAsm().toResource(account);
			return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
	public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId, @RequestBody BlogResource res) {
		try {
			Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
			BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
			return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
		} catch (AccountDoesNotExistException exception) {
			throw new BadRequestException(exception);
		}
	}

}
