package com.zlrx.springreact.springreact.repository;

import com.zlrx.springreact.springreact.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
