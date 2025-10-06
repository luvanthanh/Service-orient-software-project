package LuVanThanh.Myproject.controller;


import LuVanThanh.Myproject.entity.User;
import LuVanThanh.Myproject.request.userRequest.UserCreationRequest;
import LuVanThanh.Myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    User creationUser (@RequestBody UserCreationRequest request) {
        return userService.creationUser(request);
    }

}
