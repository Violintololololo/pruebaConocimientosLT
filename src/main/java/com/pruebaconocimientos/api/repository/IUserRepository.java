package com.pruebaconocimientos.api.repository;

import com.pruebaconocimientos.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
