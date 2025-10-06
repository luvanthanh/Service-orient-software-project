package LuVanThanh.Myproject.repository;

import LuVanThanh.Myproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
