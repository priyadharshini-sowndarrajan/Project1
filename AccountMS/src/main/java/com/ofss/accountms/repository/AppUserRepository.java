package com.ofss.accountms.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ofss.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);
}



