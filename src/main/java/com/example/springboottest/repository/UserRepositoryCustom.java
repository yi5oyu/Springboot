package com.example.springboottest.repository;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<Long> findIdByName(String name);
}
