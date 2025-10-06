package LuVanThanh.Myproject.service;


import LuVanThanh.Myproject.entity.User;
import LuVanThanh.Myproject.repository.UserRepository;
import LuVanThanh.Myproject.request.userRequest.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User creationUser( UserCreationRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserPassword(request.getUserPassword());
        user.setUserFirstName(request.getUserFirstName());
        user.setUserLastName(request.getUserLastName());
        user.setUserBirthday(request.getUserBirthday());
        user.setUserPhone(request.getUserPhone());
        return userRepository.save(user);
    }
}
