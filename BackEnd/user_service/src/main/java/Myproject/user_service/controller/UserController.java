package Myproject.user_service.controller;


import Myproject.user_service.dto.reponse.ApiResponse;
import Myproject.user_service.dto.reponse.UserResponse;
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
    ApiResponse<UserResponse> addUser(@RequestBody UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.addUser(request));
        return apiResponse;
    }

//    lấy tất cả danh sách user
    @GetMapping
    public ApiResponse<List<User>> getAllUsers(){
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.getAllUsers());
        return apiResponse;
    }

//    lấy user theo id
    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("userId") String userId){
        ApiResponse<UserResponse> apiReponse = new ApiResponse<>();
        apiReponse.setData(userService.getUserById(userId));
        return apiReponse;
    }

//    Sửa user theo id
    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUserById(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.updateUserById(request,userId));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUserById(@PathVariable("userId") String userId){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.deletedUserById(userId));
        return apiResponse;
    }

}
