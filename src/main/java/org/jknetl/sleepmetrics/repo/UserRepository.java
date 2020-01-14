package org.jknetl.sleepmetrics.repo;

import org.jknetl.sleepmetrics.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
