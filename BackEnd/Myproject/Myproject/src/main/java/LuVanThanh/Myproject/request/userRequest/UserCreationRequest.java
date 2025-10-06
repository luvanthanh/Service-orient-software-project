package LuVanThanh.Myproject.request.userRequest;


import lombok.Data;

@Data
public class UserCreationRequest {
    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userBirthday;
    private String userPhone;
}
