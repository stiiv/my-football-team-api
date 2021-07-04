package com.stiiv.myfootballteam.api.repositories;

import com.stiiv.myfootballteam.api.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    public User findByUsername(String username);
}
