package Myproject.user_service.service;


import Myproject.user_service.dto.reponse.UserReponse;
import Myproject.user_service.dto.request.UserCreationRequest;
import Myproject.user_service.dto.request.UserUpdateRequest;
import Myproject.user_service.entity.User;
import Myproject.user_service.exception.AppException;
import Myproject.user_service.exception.ErrorCode;
import Myproject.user_service.mapper.UserMapper;
import Myproject.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserReponse addUser(@RequestBody UserCreationRequest request){
        if(userRepository.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.USER_EXITS);

        User user = userMapper.toUser(request);
        userRepository.save(user);

        return userMapper.toUserReponse(user);
    }

    public List<User> getAllUsers(){
        List<User> listUser = userRepository.findAll();
        return listUser;
    }

    public UserReponse getUserById(String userId){
        User user = userRepository.findByUserId(userId);
        return userMapper.toUserReponse(user);
    }

    public UserReponse updateUserById(@RequestBody UserUpdateRequest request, String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXITS));
        user =userMapper.toUpdateUser(user, request);
        return userMapper.toUserReponse(userRepository.save(user));

    }

    public String deletedUserById(String userId){
        User user = userRepository.findByUserId(userId);
        userRepository.delete(user);
        return " user has been deleted ";
    }
}
