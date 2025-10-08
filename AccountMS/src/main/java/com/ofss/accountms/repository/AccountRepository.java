package com.ofss.accountms.repository;


import org.springframework.data.repository.CrudRepository;

import com.ofss.accountms.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {}
