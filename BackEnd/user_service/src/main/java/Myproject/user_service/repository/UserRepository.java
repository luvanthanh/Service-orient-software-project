package Myproject.user_service.repository;

import Myproject.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    boolean  existsByUserName(String userName);
    User findByUserName(String userName);
    User findByUserId(String userId);
}
