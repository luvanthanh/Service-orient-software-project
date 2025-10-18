package Myproject.user_service.controller;


import Myproject.user_service.dto.reponse.ApiReponse;
import Myproject.user_service.dto.reponse.UserReponse;
import Myproject.user_service.dto.request.UserCreationRequest;
import Myproject.user_service.dto.request.UserUpdateRequest;
import Myproject.user_service.entity.User;
import Myproject.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;



//  thêm user mới
    @PostMapping
    ApiReponse<UserReponse> addUser(@RequestBody UserCreationRequest request){
        ApiReponse<UserReponse> apiReponse = new ApiReponse<>();
        apiReponse.setData(userService.addUser(request));
        return apiReponse;
    }

//    lấy tất cả danh sách user
    @GetMapping
    public ApiReponse<List<User>> getAllUsers(){
        ApiReponse<List<User>> apiReponse = new ApiReponse<>();
        apiReponse.setData(userService.getAllUsers());
        return apiReponse;
    }

//    lấy user theo id
    @GetMapping("/{userId}")
    public ApiReponse<UserReponse> getUserById(@PathVariable("userId") String userId){
        ApiReponse<UserReponse> apiReponse = new ApiReponse<>();
        apiReponse.setData(userService.getUserById(userId));
        return apiReponse;
    }

//    Sửa user theo id
    @PutMapping("/{userId}")
    ApiReponse<UserReponse> updateUserById(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId){
        ApiReponse<UserReponse> apiReponse = new ApiReponse<>();
        apiReponse.setData(userService.updateUserById(request,userId));
        return apiReponse;
    }

    @DeleteMapping("/{userId}")
    ApiReponse<String> deleteUserById(@PathVariable("userId") String userId){
        ApiReponse<String> apiReponse = new ApiReponse<>();
        apiReponse.setData(userService.deletedUserById(userId));
        return apiReponse;
    }

}
