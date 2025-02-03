package com.example.springboottest.repository;

import com.example.springboottest.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom  {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Optional<Long> findIdByName(String name) {
        QUser user = QUser.user;

        Long userId = queryFactory
            .select(user.id)
            .from(user)
            .where(user.name.eq(name))
            .fetchOne();

        return Optional.ofNullable(userId);
    }
}
