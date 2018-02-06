/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package org.mcare.acl.service;

import javax.inject.Inject;

import org.mcare.acl.entity.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Basic {@link AccountService} implementation.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class AccountServiceImpl implements AccountService {
	
	@Inject
	private AccountDao accountDao;
	
	/* (non-Javadoc)
	 * @see com.springinpractice.ch07.service.AccountService#getAccountByUsername(java.lang.String)
	 */
	@PreAuthorize("hasRole('PERM_READ_ACCOUNTS')")
	public Account getAccountByUsername(String username) {
		return accountDao.getByUsername(username);
	}
}