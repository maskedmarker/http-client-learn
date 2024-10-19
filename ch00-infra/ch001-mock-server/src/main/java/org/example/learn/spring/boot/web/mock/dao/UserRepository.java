package org.example.learn.spring.boot.web.mock.dao;

import org.example.learn.spring.boot.web.mock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
