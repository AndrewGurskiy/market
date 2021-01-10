package by.andrew.demo.repository;

import by.andrew.demo.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByUsername(String username);
}
